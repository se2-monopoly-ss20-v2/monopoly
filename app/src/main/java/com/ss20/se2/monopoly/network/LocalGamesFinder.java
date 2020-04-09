package com.ss20.se2.monopoly.network;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;

import java.util.ArrayList;
import java.util.List;

public class LocalGamesFinder{

	// Singleton instance
	private static LocalGamesFinder instance;
	// Instance variables
	private NsdManager.DiscoveryListener discoveryListener;
	private List<OnLocalGamesChangedListener> listeners;
	private List<LocalGame> foundLocalGames;

	/**
	 * Use the getInstance() method to get the instance of this class
	 */
	private LocalGamesFinder(){
		initializeDiscoveryListener();
		this.listeners = new ArrayList<>();
		this.foundLocalGames = new ArrayList<>();
	}

	public static LocalGamesFinder getInstance(){
		if (instance == null){
			instance = new LocalGamesFinder();
		}
		return instance;
	}

	private void initializeDiscoveryListener(){
		discoveryListener = new NsdManager.DiscoveryListener(){
			@Override
			public void onStartDiscoveryFailed(String serviceType, int errorCode){
			}

			@Override
			public void onStopDiscoveryFailed(String serviceType, int errorCode){
			}

			@Override
			public void onDiscoveryStarted(String serviceType){
			}

			@Override
			public void onDiscoveryStopped(String serviceType){
			}

			@Override
			public void onServiceFound(NsdServiceInfo serviceInfo){
				// Check if the found service is provided by a monopoly app
				// If there are several services called monopoly, then an incrementing number is
				// always added to the end of the name. Therefore, we do not look at an exact match
				if (serviceInfo.getServiceName().contains(NetworkUtilities.NSD_SERVICE_NAME)){
					foundLocalGames.add(new LocalGame(serviceInfo.getHost(), serviceInfo.getPort()));
					notifyListeners();
				}
			}

			@Override
			public void onServiceLost(final NsdServiceInfo serviceInfo){
				if (serviceInfo.getServiceName().contains(NetworkUtilities.NSD_SERVICE_NAME)){
					for (LocalGame game : foundLocalGames){
						if (game.getAddress() == serviceInfo.getHost() && game.getPort() == serviceInfo.getPort()){
							foundLocalGames.remove(game);
						}
					}
					notifyListeners();
				}
			}
		};
	}

	/**
	 * Starts the search for games in the area
	 *
	 * @param context
	 */
	public void startGameSearchInNetwork(Context context){
		NsdManager manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
		manager.discoverServices(NetworkUtilities.NSD_SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
	}

	/**
	 * Stops searching for games in the area
	 *
	 * @param context
	 */
	public void stopGameSearchInNetwork(Context context){
		NsdManager manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
		manager.stopServiceDiscovery(discoveryListener);
	}

	/**
	 * With this method you can subscribe to found games.The passed listener
	 * is notified when games are found and possibly lost again (due to malfunctions for example)
	 *
	 * @param listener
	 */
	public void subscribe(OnLocalGamesChangedListener listener){
		listeners.add(listener);
	}

	/**
	 * Removes the listener and he will not be notified anymore. May lead to performance
	 * improvements,since not all listeners have to be notified.
	 *
	 * @param listener
	 */
	public void unsubscribe(OnLocalGamesChangedListener listener){
		listeners.remove(listener);
	}

	/**
	 * Notifies all listeners that there are new changes
	 */
	private void notifyListeners(){
		for (OnLocalGamesChangedListener listener : listeners){
			listener.onGamesChanged(foundLocalGames);
		}
	}
}
