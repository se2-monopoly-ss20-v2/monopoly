package com.ss20.se2.monopoly.network;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import com.ss20.se2.monopoly.models.LocallyFoundGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LocalGamesFinder{

	// Singleton instance
	private static LocalGamesFinder instance;
	// Instance variables
	private NsdManager.DiscoveryListener discoveryListener;
	private List<OnLocalGamesChangedListener> listeners;
	private List<LocallyFoundGame> foundLocalGames;
	private boolean searching;

	/**
	 * Use the getInstance() method to get the instance of this class
	 */
	private LocalGamesFinder(){
		this.listeners = new ArrayList<>();
		this.foundLocalGames = new ArrayList<>();
		this.searching = false;
	}

	public static LocalGamesFinder getInstance(){
		if (instance == null){
			instance = new LocalGamesFinder();
		}
		return instance;
	}

	public boolean isSearching(){
		return searching;
	}

	private void initializeDiscoveryListener(final NsdManager manager){
		this.discoveryListener = new NsdManager.DiscoveryListener(){
			@Override
			public void onStartDiscoveryFailed(String serviceType, int errorCode){
				//Needs to be overridden but isn't needed in the project
			}

			@Override
			public void onStopDiscoveryFailed(String serviceType, int errorCode){
				Log.i("MonopolyApp", "onDiscoveryFailed");
			}

			@Override
			public void onDiscoveryStarted(String serviceType){
				Log.i("MonopolyApp", "onDiscoveryStart");
			}

			@Override
			public void onDiscoveryStopped(String serviceType){
				//Needs to be overridden but isn't needed in the project
			}

			@Override
			public void onServiceFound(final NsdServiceInfo serviceInfo){
				// Check if the found service is provided by a monopoly app
				// If there are several services called monopoly, then an incrementing number is
				// always added to the end of the name. Therefore, we do not look at an exact match
				if (serviceInfo.getServiceName().contains(NetworkUtilities.NSD_SERVICE_NAME)){
					manager.resolveService(serviceInfo, makeResolveListener());
					Log.d(NetworkUtilities.TAG, "Game found");
				}
			}

			@Override
			public void onServiceLost(final NsdServiceInfo serviceInfo){
				if (serviceInfo.getServiceName().contains(NetworkUtilities.NSD_SERVICE_NAME)){
					manager.resolveService(serviceInfo, makeResolveListener());
					Log.d(NetworkUtilities.TAG, "Game lost");
				}
			}
		};
	}

	/**
	 * Starts the search for games in the area.
	 * running and has not been closed.
	 *
	 * @param context
	 */
	public void startGameSearchInNetwork(Context context){
		if (!this.searching){
			NsdManager manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
			// Create each time new listener, else the NsdManager thinks its the same
			initializeDiscoveryListener(manager);
			manager.discoverServices(NetworkUtilities.NSD_SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
			this.searching = true;
			Log.d(NetworkUtilities.TAG, "Game search started");
		}
	}

	/**
	 * Stops searching for games in the area
	 *
	 * @param context
	 */
	public void stopGameSearchInNetwork(Context context){
		if (this.searching){
			foundLocalGames = new LinkedList<>();
			NsdManager manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
			manager.stopServiceDiscovery(discoveryListener);
			this.searching = false;
		}
	}

	/**
	 * With this method you can subscribe to found games.The passed listener
	 * is notified when games are found and possibly lost again (due to malfunctions for example)
	 *
	 * @param listener
	 */
	public void subscribe(OnLocalGamesChangedListener listener){
		listeners.add(listener);
		notifyListeners();
	}

	/**
	 * Removes the listener and he will not be notified anymore. May lead to performance
	 * improvements,since not all listeners have to be notified.
	 *
	 * @param listener
	 */
	public void unsubscribe(OnLocalGamesChangedListener listener){
		listeners.remove(listener);
		notifyListeners();
	}

	/**
	 * Notifies all listeners that there are new changes
	 */
	private void notifyListeners(){
		for (OnLocalGamesChangedListener listener : listeners){
			listener.onGamesChanged(foundLocalGames);
		}
	}

	private NsdManager.ResolveListener makeResolveListener(){
		return new NsdManager.ResolveListener(){
			@Override
			public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode){
				// Called when the resolve fails. Use the error code to debug.
				Log.e(NetworkUtilities.TAG, "Resolve failed: " + errorCode);
			}

			@Override
			public void onServiceResolved(NsdServiceInfo serviceInfo){
				Log.e(NetworkUtilities.TAG, "Resolve Succeeded. " + serviceInfo);
				LocallyFoundGame game = null;
				for (LocallyFoundGame foundLocalGame : foundLocalGames){
					if (serviceInfo.getHost().equals(foundLocalGame.getAddress()) && serviceInfo.getPort() == foundLocalGame.getPort()){
						game = foundLocalGame;
					}
				}
				if (game == null){
					foundLocalGames.add(new LocallyFoundGame(serviceInfo.getHost(), serviceInfo.getPort()));
				}else{
					foundLocalGames.remove(game);
				}
				notifyListeners();
			}
		};
	}
}
