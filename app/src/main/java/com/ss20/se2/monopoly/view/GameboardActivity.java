package com.ss20.se2.monopoly.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ss20.se2.monopoly.DeedManager;
import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.*;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.network.gamestate.GameStateNetworkMessage;
import com.ss20.se2.monopoly.network.gamestate.OnGameStateChangedListener;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.cards.Card;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.network.client.GameController;
import com.ss20.se2.monopoly.network.server.GameServer;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.models.fields.specials.Special;
import com.ss20.se2.monopoly.models.fields.specials.SpecialFieldType;
import com.ss20.se2.monopoly.view.deed.DeedFragment;
import com.ss20.se2.monopoly.view.deed.DeedFragmentDelegate;
import com.ss20.se2.monopoly.view.dialog.DialogContainerFragment;

import java.util.List;

public class GameboardActivity extends AppCompatActivity implements DeedFragmentDelegate{

	Button button_rollDice;
	TextView view_numberDice;
	TextView view_numberDice2;
	TextView viewdoubles;
	TextView view_position;
	TextView view_balance;
	TextView updateBalance;

	ImageView boat;
	ImageView car;
	ImageView cat;
	ImageView dino;
	ImageView dog;
	ImageView duck;
	ImageView hat;
	ImageView penguin;

	Dice dice = new Dice();
	Dice dice2 = new Dice();
	Gameboard gameboard;
	DeedManager deedManager;

	Player currentPlayer;
	ChanceCardDeck chanceCards;
	CommunityCardDeck communityCards;
	ChanceCardProcessor chanceCardProcessor;
	CommunityCardProcessor communityCardProcessor;
	int oldBalance;

	int amount;
	int roll1;
	int roll2;
	int doublescounter;

	boolean isHost;
	Context context = this;

	public ImageView[] initializeUI(){
		ImageView field0 = findViewById(R.id.tile_0);
		ImageView field1 = findViewById(R.id.tile_1);
		ImageView field2 = findViewById(R.id.tile_2);
		ImageView field3 = findViewById(R.id.tile_3);
		ImageView field4 = findViewById(R.id.tile_4);
		ImageView field5 = findViewById(R.id.tile_5);
		ImageView field6 = findViewById(R.id.tile_6);
		ImageView field7 = findViewById(R.id.tile_7);
		ImageView field8 = findViewById(R.id.tile_8);
		ImageView field9 = findViewById(R.id.tile_9);
		ImageView field10 = findViewById(R.id.tile_10);
		ImageView field11 = findViewById(R.id.tile_11);
		ImageView field12 = findViewById(R.id.tile_12);
		ImageView field13 = findViewById(R.id.tile_13);
		ImageView field14 = findViewById(R.id.tile_14);
		ImageView field15 = findViewById(R.id.tile_15);
		ImageView field16 = findViewById(R.id.tile_16);
		ImageView field17 = findViewById(R.id.tile_17);
		ImageView field18 = findViewById(R.id.tile_18);
		ImageView field19 = findViewById(R.id.tile_19);
		ImageView field20 = findViewById(R.id.tile_20);
		ImageView field21 = findViewById(R.id.tile_21);
		ImageView field22 = findViewById(R.id.tile_22);
		ImageView field23 = findViewById(R.id.tile_23);
		ImageView field24 = findViewById(R.id.tile_24);
		ImageView field25 = findViewById(R.id.tile_25);
		ImageView field26 = findViewById(R.id.tile_26);
		ImageView field27 = findViewById(R.id.tile_27);
		ImageView field28 = findViewById(R.id.tile_28);
		ImageView field29 = findViewById(R.id.tile_29);
		ImageView field30 = findViewById(R.id.tile_30);
		ImageView field31 = findViewById(R.id.tile_31);
		ImageView field32 = findViewById(R.id.tile_32);
		ImageView field33 = findViewById(R.id.tile_33);
		ImageView field34 = findViewById(R.id.tile_34);
		ImageView field35 = findViewById(R.id.tile_35);
		ImageView field36 = findViewById(R.id.tile_36);
		ImageView field37 = findViewById(R.id.tile_37);
		ImageView field38 = findViewById(R.id.tile_38);
		ImageView field39 = findViewById(R.id.tile_39);

		return new ImageView[]{field0, field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, field17, field18, field19, field20, field21, field22, field23, field24, field25, field26, field27, field28, field29, field30, field31, field32, field33, field34, field35, field36, field37, field38, field39};

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard_activity);


		final ImageView[] fields = initializeUI();
		chanceCards = new ChanceCardDeck();
		communityCards = new CommunityCardDeck();
		chanceCards.initializeDeck();
		communityCards.initializeDeck();
		chanceCardProcessor = new ChanceCardProcessor();
		communityCardProcessor = new CommunityCardProcessor();
		button_rollDice = findViewById(R.id.button_roll_dice);
		view_numberDice = findViewById(R.id.view_number_dice);
		view_numberDice2 = findViewById(R.id.view_number_dice2);
		viewdoubles = findViewById(R.id.doubles);
		view_position = findViewById(R.id.number_playerposition);
		view_balance = findViewById(R.id.text_balance);
		updateBalance = findViewById(R.id.changeOfBalance);


		boat = findViewById(R.id.icon_boat);
		car = findViewById(R.id.icon_car);
		cat = findViewById(R.id.icon_cat);
		dino = findViewById(R.id.icon_dino);
		dog = findViewById(R.id.icon_dog);
		duck = findViewById(R.id.icon_duck);
		hat = findViewById(R.id.icon_hat);
		penguin = findViewById(R.id.icon_penguin);



		button_rollDice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				roll1 = dice.roll();
				roll2 = dice2.roll();
				amount = roll1 + roll2;
				checkDouble(roll1, roll2);

				if (checkDouble(roll1, roll2)){
					doublescounter++;
				}else{
					doublescounter = 0;
				}

				if(doublescounter == 3){
					//Move to jail
				}

				setOldBalance(currentPlayer.getBalance());

				gameboard.move("Player 1", amount);

				updateBalance.setText("");
				currentPlayer.setCurrentPosition(gameboard.getPosition("Player 1"));
				checkPlayersPosition(currentPlayer);

				view_numberDice.setText("Roll 1: " + Integer.toString(roll1));
				view_numberDice2.setText("Roll 2: " + Integer.toString(roll2));
				view_position.setText(Integer.toString(gameboard.getPosition("Player 1")));

				findViewById(R.id.playericon).setX(fields[gameboard.getPosition("Player 1")].getX());
				findViewById(R.id.playericon).setY(fields[gameboard.getPosition("Player 1")].getY());
			}
		});

		setup();

		for (int i = 0; i < fields.length; i++) {
			final ImageView view = fields[i];
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					GameTile tile = gameboard.gameTiles.get((Integer.parseInt(v.getTag().toString())));
					showTileInfo(tile, currentPlayer);

				}
			});
		}
	}




	public void checkPlayersPosition(final Player player) {
		GameTile currentTile = gameboard.gameTiles.get(player.getCurrentPosition());

		if (currentTile instanceof Street) {
			final Street street = (Street) currentTile;

			//does it belong to someone?
			if (street.getOwner() == null) {
				FragmentManager fm = getSupportFragmentManager();
				DialogContainerFragment containerFragment = DialogContainerFragment.newInstance();
				containerFragment.setupViewModel(street, player, this);

				containerFragment.show(fm, "dialog_container_fragment");

			} else if (street.getOwner() == player && deedManager.playerOwnsAllStreetsOf(street.getColor(), player)) {

				final AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
				dialog.setTitle(getString(R.string.buyHouseTitle));
				dialog.setMessage(getString(R.string.buyHouseOnDeed, street.getName(), street.getHousePrice()));

				dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no), new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialogInterface, int i){
						dialog.dismiss();
						playerFinishedTurn();
					}
				});

				dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						int balance = deedManager.performAcquiringHouseFor(street, player);
						view_balance.setText(getString(R.string.balance,  balance));
						showDifference(getOldBalance(), player.getBalance());
						playerFinishedTurn();
					}
				});
			} else {
				//player is on his street, but no action possible
				playerFinishedTurn();
			}
		}else if (currentTile instanceof Railroad) {
			//TODO @dermutzh -> will be handled next week.
			playerFinishedTurn();
		}else if (currentTile instanceof Utility){
			//TODO @dermutzh -> will be covered next week.
			playerFinishedTurn();
		}
		else if (currentTile instanceof CommunityCard){
			CommunityCard communityCard = communityCards.getNextCard();
			AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
			dialog.setTitle("Community Card!");
			dialog.setMessage(communityCard.getDescription());
			dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					showDifference(getOldBalance(), player.getBalance());
				}
			});
			dialog.show();

			communityCardProcessor.performAction(player, communityCard);
			view_balance.setText("Balance: " + player.getBalance());


		}else if (currentTile instanceof ChanceCard){
			ChanceCard chanceCard = chanceCards.getNextCard();
			AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
			dialog.setTitle("Chance Card!");
			dialog.setMessage(chanceCard.getDescription());
			dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					showDifference(getOldBalance(), player.getBalance());
				}
			});
			dialog.show();

			chanceCardProcessor.performAction(player, chanceCard);
			view_balance.setText("Balance: " + player.getBalance());
		}
	}

	public boolean checkDouble(int roll1, int roll2){
		boolean status = false;

		if (roll1 == roll2){
			status = true;
			viewdoubles.setText("Doubles!");
			return status;
		}else{
			viewdoubles.setText(" ");
			return status;
		}

	}

	void setup() {

		OnGameStateChangedListener listener = new OnGameStateChangedListener(){
			@Override
			public void onGameStateChanged(GameState gameState){
				//react on changes. -> update the state.
				//Add update stuff here, for UI updates use method below.

				updateUI();
			}

			@Override
			public void setupGameState(GameState gameState){
				//initial setup

				gameboard = gameState.getGameboard();
				deedManager = gameState.getDeedManager();
				gameboard.gameboardArray[0] = new GamePiece("Player 1");

				if (currentPlayer == null) {
					currentPlayer = GameState.getInstance().getPlayerFrom(Lobby.getInstance().getSelf().getAddress(), Lobby.getInstance().getSelf().getPort());
				}

				for (Player player:GameState.getInstance().getPlayers()) {

					GamePiece piece = player.getSelectedPiece();
					ImageView playerView = new ImageView(context);
					int pos = player.getCurrentPosition();

					Log.d("Piece:", piece.getName());

					switch (piece.getName()){
						case ("Boat"):
							playerView.setImageResource(R.drawable.boat);
							boat.setVisibility(View.VISIBLE);

							break;
						case ("Car"):
							playerView.setImageResource(R.drawable.car);
							car.setVisibility(View.VISIBLE);

							break;

						case ("Cat"):
							playerView.setImageResource(R.drawable.cat);
							cat.setVisibility(View.VISIBLE);

							break;

						case ("Dino"):
							playerView.setImageResource(R.drawable.dino);
							dino.setVisibility(View.VISIBLE);

							break;

						case ("Dog"):
							playerView.setImageResource(R.drawable.dog);
							dog.setVisibility(View.VISIBLE);

							break;

						case ("Duck"):
							playerView.setImageResource(R.drawable.duck);
							duck.setVisibility(View.VISIBLE);

							break;

						case ("Hat"):
							playerView.setImageResource(R.drawable.hat);
							hat.setVisibility(View.VISIBLE);

							break;

						case ("Penguin"):
							playerView.setImageResource(R.drawable.penguin);
							penguin.setVisibility(View.VISIBLE);

							break;

						default: playerView.setImageResource(R.mipmap.ic_launcher);
					}

					playerView.setX(pos);
					playerView.setY(pos);

				}
				updateUI();
			}
		};

		GameState.getInstance().subscribe(listener);

		if (Lobby.getInstance().getSelf().getName().equals("SERVER")) {
			GameServer.getInstance().setupGameState(getApplicationContext());
			isHost = true;
		}
	}

	void updateUI() {
		runOnUiThread(new Runnable(){
			@Override
			public void run(){
				view_balance.setText(getString(R.string.balance,  currentPlayer.getBalance()));

				if (GameState.getInstance().getCurrentActivePlayer().getAddress().equals(currentPlayer.getAddress()) && GameState.getInstance().getCurrentActivePlayer().getPort() == currentPlayer.getPort()) {
					button_rollDice.setEnabled(true);
				} else {
					button_rollDice.setEnabled(false);
				}
				for (Player p:GameState.getInstance().getPlayers()) {
					if (!GameState.getInstance().getCurrentActivePlayer().getAddress().equals(currentPlayer.getAddress()) && GameState.getInstance().getCurrentActivePlayer().getPort() != currentPlayer.getPort()) {



					}
				}
			}
		});
	}

	//use this method if nothing happened what would change the state. (e.g. Free Parking, Player on his own Street but no Action...)
	void playerFinishedTurn() {
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());

		sendMessage(message);
	}

	void sendMessage(GameStateNetworkMessage message){
		if (isHost){
			GameServer.getInstance().updateGameState(message);
		}else {
			GameController.getInstance().updateGameState(message);
		}
	}

	/**
	 * Use this method as an example of how we update our State.
	 * 1. Do actions and modify the GameState.
	 * 2. Create new GameStateNetworkMessage
	 * 3. call sendMessage to send Update
	 *
	 * Important:
	 * Currently the update-Mechanism updates 'players', 'gameboard', 'turnRotation', 'activePlayer'
	 * if there is something you need to add, feel free to do so. (Add property to GameState + get/set and
	 * update the States for the Server: add setters to RequestHandlers method 'updateGameState'
	 * update States for Clients: add setters to ResponseHandlers method 'updateGameState'
	 */
	@Override
	public void performAcquiringDeed(Street street, Player player){

		GameState.getInstance().getDeedManager().performAcquiringDeed(street, player);
		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();

		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());

		sendMessage(message);

		view_balance.setText(getString(R.string.balance,  player.getBalance()));
		Toast.makeText(this, "You now own " + street.getName(), Toast.LENGTH_SHORT).show();
		showDifference(getOldBalance(), player.getBalance());

	}

	@Override
	public void cancelled(){
		playerFinishedTurn();
	}

	public void showDifference(int oldBalance, int newBalance){
		int difference = oldBalance-newBalance;

		if (difference == 0){
			updateBalance.setText("");
		}
		else if (oldBalance<newBalance){
			//set Color
			updateBalance.setText("+$" + difference * -1);
		}
		else {
			updateBalance.setText("-$" + difference);
		}

	}

	public void showTileInfo(GameTile gameTile, Player player) {

		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setTitle(gameTile.getName());

		if (gameTile instanceof Street) {
			Street street = (Street) gameTile;

			//separate between tiles you own, others own and nobody owns.
			//right now there is no difference between the fragments

			if (street.getOwner() != null) {
				if (street.getOwner() == player) {
					FragmentManager fm = getSupportFragmentManager();
					DeedFragment containerFragment = DeedFragment.newInstance().newInstance();
					containerFragment.createViewModel(street);

					containerFragment.show(fm, "dialog_container_fragment");
				}

				FragmentManager fm = getSupportFragmentManager();
				DeedFragment containerFragment = DeedFragment.newInstance().newInstance();
				containerFragment.createViewModel(street);

				containerFragment.show(fm, "dialog_container_fragment");
			} else {
				dialog.setMessage("No Owner");
				FragmentManager fm = getSupportFragmentManager();
				DeedFragment containerFragment = DeedFragment.newInstance().newInstance();
				containerFragment.createViewModel(street);

				containerFragment.show(fm, "dialog_container_fragment");
			}
		} else if (gameTile instanceof Railroad) {
			Railroad railroad = (Railroad) gameTile;

			if (railroad.getOwner() != null) {
				dialog.setMessage("Owner: " + railroad.getOwner().getName());
			} else {
				dialog.setMessage("No Owner");
			}
			dialog.show();
		} else if (gameTile instanceof Special) {
			Special special = (Special) gameTile;

			switch (special.getFieldType()) {
				case JAIL:
					dialog.setMessage("Landing on this tile sends you to jail immediately");
					break;

				case GO:
					dialog.setMessage("Moving past GO will get you $200");
					break;

				case FREEPARKING:
					dialog.setMessage("Nothing happens.");
					break;

				case JAIL_VISITOR:
					dialog.setMessage("If you are a visitor you can leave with your next turn. If you are jailed you need to throw a double for your escape!");
					break;
				case INCOME_TAX:
					dialog.setMessage("Landing on this tile makes you pay $200");
					break;

				case LUXURY_TAX:
					dialog.setMessage("Landing on this tile makes you pay 10% of your wealth");
					break;

				default:
					break;
			}
			dialog.show();
		} else if (gameTile instanceof Card) {
			dialog.setMessage("Surprise Cards with either good or bad effect");
			dialog.show();
		}
	}


	public int getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(int oldBalance) {
		this.oldBalance = oldBalance;
	}
}

