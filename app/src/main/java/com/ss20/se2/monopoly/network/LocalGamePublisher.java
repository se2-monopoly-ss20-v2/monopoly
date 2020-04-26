package com.ss20.se2.monopoly.network;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

public class LocalGamePublisher{

	// Singleton instance
	private static LocalGamePublisher instance;
	// Instance variables
	private NsdServiceInfo serviceInfo;
	private NsdManager.RegistrationListener registrationListener;
	private String serviceName;
	private boolean published;

	/**
	 * Use the getInstance() method to get the instance of this class
	 */
	private LocalGamePublisher(){
		this.serviceName = NetworkUtilities.NSD_SERVICE_NAME;
		this.serviceInfo = createServiceInfo(this.serviceName);
		this.published = false;
	}

	public static LocalGamePublisher getInstance(){
		if (instance == null){
			instance = new LocalGamePublisher();
		}
		return instance;
	}

	public boolean isPublished(){
		return published;
	}

	private NsdServiceInfo createServiceInfo(String serviceName){
		// Create the NsdServiceInfo object, and populate it.
		NsdServiceInfo serviceInfo = new NsdServiceInfo();
		// The name is subject to change based on conflicts
		// with other services advertised on the same network.
		serviceInfo.setServiceName(serviceName);
		serviceInfo.setServiceType(NetworkUtilities.NSD_SERVICE_TYPE);
		return serviceInfo;
	}

	private void initializeRegistrationListener(){
		registrationListener = new NsdManager.RegistrationListener(){
			@Override
			public void onServiceRegistered(NsdServiceInfo NsdServiceInfo){
				// Save the service name. Android may have changed it in order to
				// resolve a conflict, so update the name you initially requested
				// with the name Android actually used.
				serviceName = NsdServiceInfo.getServiceName();
			}

			@Override
			public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode){
				// Registration failed! Put debugging code here to determine why.
				Log.d("LocalGameProvider", "onRegistrationFailed: " + errorCode);
			}

			@Override
			public void onServiceUnregistered(NsdServiceInfo arg0){
				// Service has been unregistered. This only happens when you call
				// NsdManager.unregisterService() and pass in this listener.
				Log.i("LocalGameProvider", "onServiceUnregistered: Successfully unregistered");
			}

			@Override
			public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode){
				// Unregistration failed. Put debugging code here to determine why.
				Log.e("LocalGameProvider", "onUnregistrationFailed: " + errorCode);
			}
		};
	}

	/**
	 * Makes the game visible in the local network. Other users can then discover the IP address of
	 * the server.
	 *
	 * @param context
	 * @param port    Port on which the server on this device is accessible
	 */
	public void showGameInNetwork(Context context, int port){
		if (!this.published){
			NsdManager manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
			// Possible that the server has been terminated and has been assigned a new port
			serviceInfo.setPort(port);
			// Create each time new listener, else the NsdManager thinks its the same
			initializeRegistrationListener();
			// Publish this service to all people on the network who are looking for a service
			manager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener);
			this.published = true;
		}
	}

	/**
	 * Makes the game invisible on the local network. But if somehow the IP address is known, the
	 * server can still be accessed. Call this after starting the game and leaving the lobby.
	 *
	 * @param context
	 */
	public void hideGameInNetwork(Context context){
		if (this.published){
			NsdManager manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
			// Make the service no longer available to people in the network who are looking for services
			manager.unregisterService(registrationListener);
			this.published = false;
		}
	}
}
