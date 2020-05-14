package com.ss20.se2.monopoly;

import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.LobbyPlayer;
import com.ss20.se2.monopoly.models.OnLobbyDataChangedListener;
import com.ss20.se2.monopoly.network.client.ChangeGamePieceNetworkMessage;
import com.ss20.se2.monopoly.network.client.JoinLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.LeaveLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.client.ReadyLobbyNetworkMessage;
import com.ss20.se2.monopoly.network.server.GameServer;
import com.ss20.se2.monopoly.network.server.RequestHandler;
import com.ss20.se2.monopoly.network.shared.RequestType;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestHandlerInstrumentedTest{

	@Test
	public void testJoinLobby(){
		Lobby lobby = Lobby.getInstance();
		RequestHandler requestHandler = RequestHandler.getInstance();

		//------------------------JOIN LOBBY--------------------------------
		JoinLobbyNetworkMessage joinLobbyNetworkMessage = new JoinLobbyNetworkMessage();
		joinLobbyNetworkMessage.setSenderName("Test");
		try{
			joinLobbyNetworkMessage.setSenderAddress(InetAddress.getByName(null));
		}catch (UnknownHostException e){
			assertEquals(true,false);
		}
		joinLobbyNetworkMessage.setSenderPort(0);
		joinLobbyNetworkMessage.setType(RequestType.JOIN_GAME);
		joinLobbyNetworkMessage.setGamePiece(new GamePiece("Dino"));
		requestHandler.handleRequest(joinLobbyNetworkMessage);
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e){
			assertEquals(true,false);
		}
		assertEquals(1, lobby.getPlayers().size());

		//------------------------CHANGE PIECE--------------------------------
		ChangeGamePieceNetworkMessage changeGamePieceNetworkMessage = new ChangeGamePieceNetworkMessage();
		changeGamePieceNetworkMessage.setSenderName(joinLobbyNetworkMessage.getSenderName());
		changeGamePieceNetworkMessage.setSenderAddress(joinLobbyNetworkMessage.getSenderAddress());
		changeGamePieceNetworkMessage.setSenderPort(joinLobbyNetworkMessage.getSenderPort());
		changeGamePieceNetworkMessage.setType(RequestType.CHANGE_GAME_PIECE);
		changeGamePieceNetworkMessage.setGamePiece(new GamePiece("Hat"));
		requestHandler.handleRequest(changeGamePieceNetworkMessage);
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e){
			assertEquals(true,false);
		}
		assertEquals("Hat", lobby.getPlayers().get(0).getGamePiece().getName());

		//------------------------CHANGE PIECE--------------------------------
		ReadyLobbyNetworkMessage readyLobbyNetworkMessage = new ReadyLobbyNetworkMessage();
		readyLobbyNetworkMessage.setSenderName(joinLobbyNetworkMessage.getSenderName());
		readyLobbyNetworkMessage.setSenderAddress(joinLobbyNetworkMessage.getSenderAddress());
		readyLobbyNetworkMessage.setSenderPort(joinLobbyNetworkMessage.getSenderPort());
		readyLobbyNetworkMessage.setValue(true);
		requestHandler.handleRequest(readyLobbyNetworkMessage);
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e){
			assertEquals(true,false);
		}
		assertEquals(true, lobby.getPlayers().get(0).isReady());

		//------------------------LEAVE LOBBY--------------------------------
		LeaveLobbyNetworkMessage leaveLobbyNetworkMessage = new LeaveLobbyNetworkMessage();
		leaveLobbyNetworkMessage.setSenderAddress(joinLobbyNetworkMessage.getSenderAddress());
		leaveLobbyNetworkMessage.setSenderName(joinLobbyNetworkMessage.getSenderName());
		leaveLobbyNetworkMessage.setSenderPort(joinLobbyNetworkMessage.getSenderPort());
		requestHandler.handleRequest(leaveLobbyNetworkMessage);
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e){
			assertEquals(true,false);
		}
		assertEquals(0, lobby.getPlayers().size());
	}
}
