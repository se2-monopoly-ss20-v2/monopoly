package com.ss20.se2.monopoly.view;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss20.se2.monopoly.DeedManager;
import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.ChanceCardDeck;
import com.ss20.se2.monopoly.models.ChanceCardProcessor;
import com.ss20.se2.monopoly.models.CommunityCardDeck;
import com.ss20.se2.monopoly.models.CommunityCardProcessor;
import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.GameState;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.Lobby;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.network.gamestate.GameStateNetworkMessage;
import com.ss20.se2.monopoly.network.gamestate.OnGameStateChangedListener;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.*;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.cards.Card;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

import com.ss20.se2.monopoly.network.client.GameController;
import com.ss20.se2.monopoly.network.server.GameServer;
import com.ss20.se2.monopoly.models.fields.specials.Special;

import com.ss20.se2.monopoly.models.fields.specials.SpecialFieldType;

import com.ss20.se2.monopoly.view.deed.DeedFragment;
import com.ss20.se2.monopoly.view.deed.DeedFragmentDelegate;
import com.ss20.se2.monopoly.view.dialog.DialogContainerFragment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import java.util.Objects;


public class GameboardActivity extends AppCompatActivity implements DeedFragmentDelegate{

	Button button_rollDice;
	Button button_buyOut;
	TextView view_numberDice;
	TextView view_numberDice2;
	TextView viewdoubles;
	TextView view_position;
	TextView view_balance;
	TextView updateBalance;

	Button btnshowdeeds;
	ListView deedlistview;

	Button altbutton;
	Button cheatButton;
	Button exposeButton;
	ImageView middleTile;
	Button addUpBtn;


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
	ImageView[] fields;
	TextView[] houseFields;
	ImageView boat;
	ImageView car;
	ImageView cat;
	ImageView dino;
	ImageView dog;
	ImageView duck;
	ImageView hat;
	ImageView penguin;

	private SensorManager mSensorManager;
	float accel;
	float currentaccel;
	float lastaccel;

	int jailRollCounter;

	boolean isHost;


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

	TextView[] initalizeHouseFields() {
		TextView field1 = findViewById(R.id.textViewHouse1);
		TextView field3 = findViewById(R.id.textViewHouse3);
		TextView field6 = findViewById(R.id.textViewHouse6);
		TextView field8 = findViewById(R.id.textViewHouse8);
		TextView field9 = findViewById(R.id.textViewHouse9);
		TextView field11 = findViewById(R.id.textViewHouse11);
		TextView field13 = findViewById(R.id.textViewHouse13);
		TextView field14 = findViewById(R.id.textViewHouse14);
		TextView field16 = findViewById(R.id.textViewHouse16);
		TextView field18 = findViewById(R.id.textViewHouse18);
		TextView field19 = findViewById(R.id.textViewHouse19);
		TextView field21 = findViewById(R.id.textViewHouse21);
		TextView field23 = findViewById(R.id.textViewHouse23);
		TextView field24 = findViewById(R.id.textViewHouse24);
		TextView field26 = findViewById(R.id.textViewHouse26);
		TextView field27 = findViewById(R.id.textViewHouse27);
		TextView field29 = findViewById(R.id.textViewHouse29);
		TextView field31 = findViewById(R.id.textViewHouse31);
		TextView field32 = findViewById(R.id.textViewHouse32);
		TextView field34 = findViewById(R.id.textViewHouse34);
		TextView field37 = findViewById(R.id.textViewHouse37);
		TextView field39 = findViewById(R.id.textViewHouse39);
		TextView fieldPL = findViewById(R.id.textViewHousePL);

		return new TextView[]{field1, field3, fieldPL, field6, field8, field9, field11, fieldPL, field13, field14, fieldPL, field16, field18, field19, field21, field23, field24, fieldPL, field26, field27, fieldPL, field29, field31, field32, field34, fieldPL, field37, field39};

	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard_activity);


		fields = initializeUI();
		houseFields = initalizeHouseFields();
		chanceCards = new ChanceCardDeck();
		communityCards = new CommunityCardDeck();
		chanceCards.initializeDeck();
		communityCards.initializeDeck();
		chanceCardProcessor = new ChanceCardProcessor();
		communityCardProcessor = new CommunityCardProcessor();
		button_rollDice = findViewById(R.id.button_roll_dice);
		button_buyOut = findViewById(R.id.btn_buy_out);
		view_numberDice = findViewById(R.id.view_number_dice);
		view_numberDice2 = findViewById(R.id.view_number_dice2);
		viewdoubles = findViewById(R.id.doubles);
		view_position = findViewById(R.id.number_playerposition);
		view_balance = findViewById(R.id.text_balance);
		updateBalance = findViewById(R.id.changeOfBalance);

		btnshowdeeds = findViewById(R.id.button_deeds);
		deedlistview = findViewById(R.id.deed_list);
		altbutton = findViewById(R.id.altbutton);
		boat = findViewById(R.id.boat);
		car = findViewById(R.id.car);
		dino = findViewById(R.id.dino);
		dog = findViewById(R.id.dog);
		duck = findViewById(R.id.duck);
		hat = findViewById(R.id.hat);
		penguin = findViewById(R.id.penguin);
		addUpBtn = findViewById(R.id.addUpBtn);

		cheatButton = findViewById(R.id.button_cheat);
		middleTile = findViewById(R.id.tile_middle);
		exposeButton = findViewById(R.id.button_expose);

		button_rollDice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(), "Shake or tap to roll!", Toast.LENGTH_SHORT).show();

				mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
				accel = 9f;
				currentaccel = SensorManager.GRAVITY_EARTH;
				lastaccel = SensorManager.GRAVITY_EARTH;
				altbutton.setVisibility(View.VISIBLE);
				exposeButton.setEnabled(true);

				final SensorEventListener mSensorListener = new SensorEventListener(){

					@Override
					public void onSensorChanged(SensorEvent event){

						float x = event.values[0];
						float y = event.values[1];
						float z = event.values[2];

						lastaccel = currentaccel;
						currentaccel = (float) Math.sqrt((double) (x * x + y * y + z * z));
						float delta = currentaccel - lastaccel;
						accel = accel * 0.9f + delta;

						if(accel > 11){

							mSensorManager.unregisterListener(this);
							altbutton.setVisibility(View.GONE);

							processRoll();
						}
					}

					@Override
					public void onAccuracyChanged(Sensor sensor, int accuracy){
						//Method not needed but required for the sensor to work
					}
				};

				//Alternative to shaking the device
				altbutton.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){

						mSensorManager.unregisterListener(mSensorListener);
						altbutton.setVisibility(View.GONE);

						processRoll();

						GameTile currentTile = gameboard.gameTiles.get(currentPlayer.getCurrentPosition());

						if(currentTile instanceof Street){
							final Street street = (Street) currentTile;
							if(street.getOwner() == null){
								middleTile.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										cheatButton.setVisibility(View.VISIBLE);
										middleTile.setOnClickListener(null);
									}

								});
							}
						}

					}
				});

				Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
						SensorManager.SENSOR_DELAY_GAME);
				cheatButton.setVisibility(View.INVISIBLE);
				}
		});
		button_buyOut.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				setOldBalance(currentPlayer.getBalance());
				buyPlayerOutOfJail(currentPlayer);
				int balance = currentPlayer.getBalance();
				view_balance.setText(getString(R.string.balance, balance));
				showDifference(getOldBalance(), currentPlayer.getBalance());
			}
		});

		cheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				GameTile currentTile = gameboard.gameTiles.get(currentPlayer.getCurrentPosition());

				cheat(currentPlayer, (Street) currentTile);
				cheatButton.setVisibility(View.INVISIBLE);
				//playerFinishedTurn();
			}
		});

		exposeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
				dialog.setTitle("Expose a player!");
				dialog.setMessage("You are about to accuse your predecessor of cheating. If your suspicion is right, he will loose its cheated street and he gets to pay you a fine");
				dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Expose", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();

						expose(currentPlayer);

					}
				});
				dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				dialog.show();


				exposeButton.setEnabled(false);
				//playerFinishedTurn();
			}
		});



		btnshowdeeds.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){

				ArrayAdapter arrayAdapter = new ArrayAdapter(GameboardActivity.this, android.R.layout.simple_list_item_1,currentPlayer.getPlayersDeeds());
				deedlistview.setAdapter(arrayAdapter);

				if (deedlistview.getVisibility() == View.VISIBLE){
					deedlistview.setVisibility(View.INVISIBLE);
				}else{
					deedlistview.setVisibility(View.VISIBLE);
				}
				deedlistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id){
						if (!currentPlayer.getPlayersDeeds().get(position).getIsMortgaged()){
							mortgage(currentPlayer.getPlayersDeeds().get(position), currentPlayer);
						}else if (currentPlayer.getPlayersDeeds().get(position).getIsMortgaged()){
							payMortgage(currentPlayer.getPlayersDeeds().get(position), currentPlayer);
						}
					}
				});
			}
		});


		addUpBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				addUp(currentPlayer);
			}
		});

		setup();

		for (int i = 0; i < fields.length; i++){
			final ImageView view = fields[i];
			view.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					GameTile tile = gameboard.gameTiles.get((Integer.parseInt(v.getTag().toString())));
					showTileInfo(tile, currentPlayer);
				}
			});
		}
	}

	public void processRoll(){
			int oldPosition = currentPlayer.getCurrentPosition();
			boolean goTojailTurn = false;
			boolean isinJail = currentPlayer.isInJail();
			roll1 = dice.roll();
			roll2 = dice2.roll();
			amount = roll1 + roll2;
			checkDouble(roll1, roll2);
			if (checkDouble(roll1, roll2)){
				doublescounter++;
			}else{
				doublescounter = 0;
			}
			if (doublescounter == 3){
				int move = 10 - currentPlayer.getCurrentPosition();
				gameboard.move("Player 1", move);
				currentPlayer.setCurrentPosition(gameboard.getPosition("Player 1"));
				movePlayerToJail(currentPlayer);
				goTojailTurn = true;
			}
			setOldBalance(currentPlayer.getBalance());
			if (!goTojailTurn && !isinJail){
				gameboard.move("Player 1", amount);
				updateBalance.setText("");
				currentPlayer.setCurrentPosition(gameboard.getPosition("Player 1"));
				if (currentPlayer.getCurrentPosition() < oldPosition){
					checkGo(currentPlayer);
				}
				checkPlayersPosition(currentPlayer);
			}else if (isinJail){
				jailRollCounter++;
				if (checkDouble(roll1, roll2)){
					movePlayerOutOfJail(currentPlayer);
				}else if (jailRollCounter == 3){
					playerFinishedTurn();
					jailRollCounter = 0;
				}
			}
			view_numberDice.setText("Roll 1: " + Integer.toString(roll1));
			view_numberDice2.setText("Roll 2: " + Integer.toString(roll2));
			view_position.setText(Integer.toString(gameboard.getPosition("Player 1")));
			exposeButton.setEnabled(false);
			setPlayerIcon(currentPlayer);
	}

	public void checkPlayersPosition(final Player player){
		GameTile currentTile = gameboard.gameTiles.get(player.getCurrentPosition());
		if (currentTile instanceof Street){
			final Street street = (Street) currentTile;
			if (street.getIsMortgaged()){
				playerFinishedTurn();
			}else{
				handlePlayerOnStreet(street, player);
			}
		}else if (currentTile instanceof Railroad) {
			Railroad railroad = (Railroad) currentTile;
			if (railroad.getIsMortgaged()){
				playerFinishedTurn();
			}else{
				handlePlayerOnRailroad(railroad, player);
			}
		}else if (currentTile instanceof Utility){
			Utility utility = (Utility) currentTile;
			if(utility.getIsMortgaged()){
				playerFinishedTurn();
			}else{
				handlePlayerOnUtility(utility, player);
			}
		}else if (currentTile instanceof Special){
			Special tile = (Special) currentTile;
			if (tile.getFieldType().equals(SpecialFieldType.FREEPARKING)){
				doFreeParking();
			}else if (tile.getFieldType().equals(SpecialFieldType.INCOME_TAX)){
				payIncomeTax(currentPlayer);
			}else if (tile.getFieldType().equals(SpecialFieldType.LUXURY_TAX)){
				payLuxuryTax(currentPlayer);
			}else if (tile.getFieldType().equals(SpecialFieldType.JAIL)){
				movePlayerToJail(currentPlayer);
				gameboard.move("Player 1", -20);
			}else if (tile.getFieldType().equals(SpecialFieldType.JAIL_VISITOR)){
				playerFinishedTurn();
			}else if (tile.getFieldType().equals(SpecialFieldType.GO)){
				playerFinishedTurn();
			}
		}else if (currentTile instanceof CommunityCard){

			CommunityCard communityCard = communityCards.getNextCard();
			AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
			dialog.setTitle("Community Card!");
			dialog.setMessage(communityCard.getDescription());
			dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which){
					dialog.dismiss();
					showDifference(getOldBalance(), player.getBalance());
				}
			});
			dialog.show();
			communityCardProcessor.performAction(player, communityCard);
			view_balance.setText("Balance: " + player.getBalance());
			checkGameOver(player);

			GameState.getInstance().updatePlayer(player);
			playerFinishedTurn();

		}else if (currentTile instanceof ChanceCard){
			ChanceCard chanceCard = chanceCards.getNextCard();
			AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
			dialog.setTitle("Chance Card!");
			dialog.setMessage(chanceCard.getDescription());
			dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which){
					dialog.dismiss();
					showDifference(getOldBalance(), player.getBalance());
				}
			});
			dialog.show();
			chanceCardProcessor.performAction(player, chanceCard);
			view_balance.setText("Balance: " + player.getBalance());
			checkGameOver(player);

			GameState.getInstance().updatePlayer(player);
			playerFinishedTurn();
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


	void setup(){
		OnGameStateChangedListener listener = new OnGameStateChangedListener(){
			@Override
			public void onGameStateChanged(GameState gameState){
				//react on changes. -> update the state.
				//Add update stuff here, for UI updates use method below.

				gameboard.gameTiles = gameState.getGameboard().getGameTiles();
				int differentBalance = GameState.getInstance().getBalanceOfSpecificPlayer(currentPlayer);
				if (differentBalance != currentPlayer.getBalance()) {
					currentPlayer.setBalance(differentBalance);
				}

				updateAllStreets();

				updateUI();
			}

			@Override
			public void setupGameState(GameState gameState){
				//initial setup
				gameboard = gameState.getGameboard();
				deedManager = gameState.getDeedManager();
				gameboard.gameboardArray[0] = new GamePiece("Player 1");
				if (currentPlayer == null){
					currentPlayer = GameState.getInstance().getPlayerFrom(Lobby.getInstance().getSelf().getAddress(), Lobby.getInstance().getSelf().getPort());
				}
				updateUI();
			}
		};
		GameState.getInstance().subscribe(listener);
		if (Lobby.getInstance().getSelf().getName().equals("SERVER")){
			GameServer.getInstance().setupGameState(getApplicationContext());
			isHost = true;
		}
	}

	void updateUI(){
		runOnUiThread(new Runnable(){
			@Override
			public void run(){
				view_balance.setText(getString(R.string.balance, currentPlayer.getBalance()));
				if (GameState.getInstance().getCurrentActivePlayer().getAddress().equals(currentPlayer.getAddress()) && GameState.getInstance().getCurrentActivePlayer().getPort() == currentPlayer.getPort()){
					button_rollDice.setEnabled(true);
					if (currentPlayer.isInJail() && currentPlayer.getBalance() > 50){
						button_buyOut.setEnabled(true);
					}else{
						button_buyOut.setEnabled(false);
					}
				}else{
					button_rollDice.setEnabled(false);
					button_buyOut.setEnabled(false);
				}
				setPlayerIcons(currentPlayer);
			}
		});
	}

	//use this method if nothing happened what would change the state. (e.g. Free Parking, Player on his own Street but no Action...)
	void playerFinishedTurn(){
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
	}

	void sendMessage(GameStateNetworkMessage message){
		if (isHost){
			GameServer.getInstance().updateGameState(message);
		}else{
			GameController.getInstance().updateGameState(message);
		}
	}

	void openBuyDialog(Deed deed, Player player) {
		FragmentManager fm = getSupportFragmentManager();
		DialogContainerFragment containerFragment = DialogContainerFragment.newInstance();
		containerFragment.setupViewModel(deed, player, this);
		containerFragment.show(fm, "dialog_container_fragment");
	}

	int getDueRentOfUtility(Player player) {
		int multiplicator = 4;

		if (GameState.getInstance().playerOwnsBothUtilities(player)) {
			multiplicator = 10;
		}

		return (roll1 + roll2) * multiplicator;
	}

	void showToast(int rent, String playerName) {
		Toast.makeText(this, getString(R.string.youPaidTo, rent, playerName), Toast.LENGTH_LONG).show();
	}

	void updatePlayersAfterPaymentAndEndTurn(Player owner, Player player) {
		GameState.getInstance().updatePlayer(owner);
		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();

		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());

		sendMessage(message);
	}

	void handlePlayerOnStreet(final Street street, final Player player) {
		if (street.getOwner() == null) {
			openBuyDialog(street, player);

			/*
				The playerOwnsAllStreetsOf(_:) has been removed due to the missing trading function.
				deedManager.playerOwnsAllStreetsOf(street.getColor(), player)
			 */
		}else if (street.getOwner().getAddress().equals(player.getAddress()) && street.getOwner().getPort() == player.getPort()  && !street.getHasHotel()) {
			final AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
			dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no), new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialogInterface, int i){
					dialog.dismiss();
					GameState.getInstance().updatePlayer(player);

					playerFinishedTurn();
				}
			});

			int headerId = 0;
			int messageId = 0;

			if (street.getHouseCount() == 4) {
				//Player is eligible to upgrade to hotel.
				headerId = R.string.buyHotelHeader;
				messageId = R.string.buyHotelOnDeed;
			} else {
				headerId = R.string.buyHouseTitle;
				messageId = R.string.buyHouseOnDeed;
			}

			dialog.setTitle(getString(headerId));
			dialog.setMessage(getString(messageId, street.getName(), street.getHousePrice()));

			dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which){
					dialog.dismiss();
					int balance = street.getHouseCount() == 4 ? deedManager.performAcquiringHotelFor(street, player) : deedManager.performAcquiringHouseFor(street, player);
					GameState.getInstance().updateStreet(street);
					view_balance.setText(getString(R.string.balance,  balance));
					showDifference(getOldBalance(), player.getBalance());
					playerFinishedTurn();
				}
			});

			dialog.show();
		} else if (!street.getOwner().getAddress().equals(player.getAddress()) && street.getOwner().getPort() != player.getPort()) {
			//hostile owns it.
			player.setBalance(player.getBalance() - street.getCurrentRent());
			street.getOwner().setBalance(street.getOwner().getBalance() + street.getCurrentRent());

			checkGameOver(player);
			updatePlayersAfterPaymentAndEndTurn(street.getOwner(), player);

			showToast(street.getCurrentRent(), street.getOwner().getName());
		} else {
			//player is on his street, but no action possible
			playerFinishedTurn();
		}
	}

	void handlePlayerOnRailroad(Railroad railroad, Player player) {
		if (railroad.getOwner() == null) {
			openBuyDialog(railroad, player);

		} else if (!railroad.getOwner().getAddress().equals(player.getAddress()) && railroad.getOwner().getPort() != player.getPort()){
			Player owner = railroad.getOwner();
			int count = GameState.getInstance().countOfPlayersRailroads(owner);
			int due = railroad.getRentRelativeTo(count);

			player.setBalance(player.getBalance() - due);
			owner.setBalance(owner.getBalance() + due);

			checkGameOver(player);
			updatePlayersAfterPaymentAndEndTurn(owner, player);

			showToast(due, owner.getName());
		} else {
			playerFinishedTurn();
		}
	}

	void handlePlayerOnUtility(Utility utility, Player player) {
		if (utility.getOwner() == null) {
			openBuyDialog(utility, player);

		} else if (!utility.getOwner().getAddress().equals(player.getAddress()) && utility.getOwner().getPort() != player.getPort()) {
			Player owner = utility.getOwner();
			int dueRent = getDueRentOfUtility(owner);
			player.setBalance(player.getBalance() - dueRent);
			owner.setBalance(owner.getBalance() + dueRent);

			checkGameOver(player);
			updatePlayersAfterPaymentAndEndTurn(owner, player);

			showToast(dueRent, owner.getName());
		} else {
			//is players own utility
			playerFinishedTurn();
		}
	}

	/**
	 * Use this method as an example of how we update our State.
	 * 1. Do actions and modify the GameState.
	 * 2. Create new GameStateNetworkMessage
	 * 3. call sendMessage to send Update
	 * <p>
	 * Important:
	 * Currently the update-Mechanism updates 'players', 'gameboard', 'turnRotation', 'activePlayer'
	 * if there is something you need to add, feel free to do so. (Add property to GameState + get/set and
	 * update the States for the Server: add setters to RequestHandlers method 'updateGameState'
	 * update States for Clients: add setters to ResponseHandlers method 'updateGameState'
	 */
	@Override

	public void performAcquiringDeed(Deed deed, Player player){

		if (deed.getPrice() <= player.getBalance()) {
			//PLAYER CAN BUY IT
			int newBalance = player.getBalance() - deed.getPrice();
			player.updateBalance(newBalance);
			player.addDeedToPlayer(deed);
			deed.setOwner(player);
			GameState.getInstance().updateDeed(deed);
		}

		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
		view_balance.setText(getString(R.string.balance, player.getBalance()));
		Toast.makeText(this, "You now own " + deed.getName(), Toast.LENGTH_SHORT).show();
		showDifference(getOldBalance(), player.getBalance());
	}

	private void checkGo(Player player){
		currentPlayer.setBalance(currentPlayer.getBalance() + 200);
		player.setBalance(player.getBalance());
		GameState.getInstance().updatePlayer(player);
		view_balance.setText(getString(R.string.balance, player.getBalance()));
		showDifference(getOldBalance(), player.getBalance());
	}

	private void movePlayerToJail(Player player){
		currentPlayer.setInJail(true);
		currentPlayer.setCurrentPosition(10);
		player.setInJail(true);
		player.setCurrentPosition(10);
		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setTitle("Jail!");
		dialog.setMessage("You have landed in jail, because you have been caught riding your snail at 100 km/h near a school.");
		dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	private void movePlayerOutOfJail(Player player){
		currentPlayer.setInJail(false);
		player.setInJail(false);
		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
	}

	private void buyPlayerOutOfJail(Player player){
		currentPlayer.setBalance(currentPlayer.getBalance() - 50);
		player.setBalance(currentPlayer.getBalance());
		movePlayerOutOfJail(player);
	}

	public void payLuxuryTax(Player player){
		player.setBalance(player.getBalance() - 100);
		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
		view_balance.setText(getString(R.string.balance, player.getBalance()));
		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setTitle("Luxury Tax!");
		dialog.setMessage("Pay 100 as luxury Tax.");
		dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		dialog.show();
		checkGameOver(player);
		showDifference(getOldBalance(), player.getBalance());
	}

	public void doFreeParking(){
		GameState.getInstance().updatePlayer(currentPlayer);
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setTitle("Free Parking!");
		dialog.setMessage("Free parking. Have a nice stay.");
		dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public void payIncomeTax(Player player){
		player.setBalance(player.getBalance() - 200);
		GameState.getInstance().updatePlayer(player);
		GameState.getInstance().playerEndedTurn();
		GameStateNetworkMessage message = new GameStateNetworkMessage();
		message.setState(GameState.getInstance());
		sendMessage(message);
		view_balance.setText(getString(R.string.balance, player.getBalance()));
		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setTitle("Income Tax!");
		dialog.setMessage("Pay 200 as income Tax.");
		dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		dialog.show();
		checkGameOver(player);
		showDifference(getOldBalance(), player.getBalance());
	}

	@Override
	public void cancelled(){
		playerFinishedTurn();
	}

	/**
	 * All changes of balance are shown in upper left corner
	 *
	 * @param oldBalance Balance the player had at turn start
	 * @param newBalance Balance the player has after a turn
	 */
	public void showDifference(int oldBalance, int newBalance){
		int difference = Math.abs(oldBalance - newBalance);
		if (difference == 0){
			updateBalance.setText("");
		}else if (oldBalance < newBalance){
			//set Color
			updateBalance.setText("+$" + difference);
		}else{
			updateBalance.setText("-$" + difference);
		}
	}

	/**
	 * Will show a description of a gametile after tapping on it
	 *
	 * @param gameTile Gametile the player is tapping on
	 * @param player Player that is tapping on the Gametile
	 */
	public void showTileInfo(GameTile gameTile, Player player){
		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setTitle(gameTile.getName());
		if (gameTile instanceof Street){
			Street street = (Street) gameTile;
			//separate between tiles you own, others own and nobody owns.
			//right now there is no difference between the fragments
			if (street.getOwner() != null){
				if (street.getOwner() == player){
					FragmentManager fm = getSupportFragmentManager();
					DeedFragment containerFragment = DeedFragment.newInstance().newInstance();
					containerFragment.createViewModel(street);
					containerFragment.show(fm, "dialog_container_fragment");
				}
				FragmentManager fm = getSupportFragmentManager();
				DeedFragment containerFragment = DeedFragment.newInstance().newInstance();
				containerFragment.createViewModel(street);
				containerFragment.show(fm, "dialog_container_fragment");
			}else{
				dialog.setMessage("No Owner");
				FragmentManager fm = getSupportFragmentManager();
				DeedFragment containerFragment = DeedFragment.newInstance().newInstance();
				containerFragment.createViewModel(street);
				containerFragment.show(fm, "dialog_container_fragment");
			}
		}else if (gameTile instanceof Railroad){
			Railroad railroad = (Railroad) gameTile;
			if (railroad.getOwner() != null){
				dialog.setMessage("Owner: " + railroad.getOwner().getName());
			}else{
				dialog.setMessage("No Owner");
			}
			dialog.show();
		}else if (gameTile instanceof Special){
			Special special = (Special) gameTile;
			switch (special.getFieldType()){
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
		}else if (gameTile instanceof Card){
			dialog.setMessage("Surprise Cards with either good or bad effect");
			dialog.show();
		}
	}

	public void mortgage( final GameTile gameTile, final Player player){

		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setTitle("Mortgage");
		dialog.setMessage("Do you want to mortgage your deed?");
		dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		dialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				int balance = player.getBalance();
				String s = "You mortgaged ";

				if(gameTile instanceof Street){
					Street street = (Street) gameTile;
					int mortgage = street.getMortgage();
					player.setBalance(balance + mortgage);
					street.setIsMortgaged(true);

					view_balance.setText(getString(R.string.balance,  player.getBalance()));
					Toast.makeText(GameboardActivity.this, s + street.getName(), Toast.LENGTH_SHORT).show();
					showDifference(getOldBalance(), player.getBalance());

				}else if (gameTile instanceof Railroad){
					Railroad railroad = (Railroad) gameTile;
					int mortgage = railroad.getMortgage();
					player.setBalance(balance + mortgage);
					railroad.setIsMortgaged(true);

					view_balance.setText(getString(R.string.balance,  player.getBalance()));
					Toast.makeText(GameboardActivity.this, s + railroad.getName(), Toast.LENGTH_SHORT).show();
					showDifference(getOldBalance(), player.getBalance());

				}else if (gameTile instanceof Utility){
					Utility utility = (Utility) gameTile;
					int mortgage = utility.getMortgage();
					player.setBalance(balance + mortgage);
					utility.setIsMortgaged(true);

					view_balance.setText(getString(R.string.balance,  player.getBalance()));
					Toast.makeText(GameboardActivity.this, s + utility.getName(), Toast.LENGTH_SHORT).show();
					showDifference(getOldBalance(), player.getBalance());
				}
			}
		});
		dialog.show();

	}

	public void payMortgage(final GameTile gameTile, final Player player){

		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setTitle("Pay Mortgage");
		dialog.setMessage("Do you want to pay your mortgage?");
		dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		dialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				int balance = player.getBalance();
				String s = "You paid the mortgage for ";

				if(gameTile instanceof Street){
					Street street = (Street) gameTile;
					int mortgage = street.getMortgage();
					player.setBalance(balance - mortgage);
					street.setIsMortgaged(false);

					view_balance.setText(getString(R.string.balance,  player.getBalance()));
					Toast.makeText(GameboardActivity.this, s + street.getName(), Toast.LENGTH_SHORT).show();
					showDifference(getOldBalance(), player.getBalance());

				}else if (gameTile instanceof Railroad){
					Railroad railroad = (Railroad) gameTile;
					int mortgage = railroad.getMortgage();
					player.setBalance(balance - mortgage);
					railroad.setIsMortgaged(false);

					view_balance.setText(getString(R.string.balance,  player.getBalance()));
					Toast.makeText(GameboardActivity.this, s + railroad.getName(), Toast.LENGTH_SHORT).show();
					showDifference(getOldBalance(), player.getBalance());

				}else if (gameTile instanceof Utility){
					Utility utility = (Utility) gameTile;
					int mortgage = utility.getMortgage();
					player.setBalance(balance - mortgage);
					utility.setIsMortgaged(false);

					view_balance.setText(getString(R.string.balance,  player.getBalance()));
					Toast.makeText(GameboardActivity.this, s + utility.getName(), Toast.LENGTH_SHORT).show();
					showDifference(getOldBalance(), player.getBalance());

				}
			}
		});
		dialog.show();
	}

	void updateAllStreets(){
		for (int i = 0; i < GameState.getInstance().getAllDeeds().size(); i++){
			if (GameState.getInstance().getAllDeeds().get(i) instanceof Street){
				final Street s = (Street) GameState.getInstance().getAllDeeds().get(i);
				if (s.getHouseCount() != 0){
					final int finalI = i;

					runOnUiThread(() -> {
						if (!s.getHasHotel()){
							houseFields[finalI].setText(String.valueOf(s.getHouseCount()));
						} else {
							houseFields[finalI].setText("H");
						}
					});
				}
			}
		}
	}

	/**
	 * Exposing has two outcomes:
	 * 1: positive expose -> cheater looses his streets
	 * and pays a fine to exposer
	 * 2: negative expose -> exposer pays a fine to accused player
	 * ----
	 * exposing will not and a turn
	 * cheater gets flushed
	 *
	 * ! Important for sprint meeting !
	 *  Cheating player will not loose his street after being exposed
	 *  Instead they will get a bigger penalty
	 *  This might be changed again for final presentation
	 *
	 * @param player Player that is exposing a cheater
	 */
	public void expose(Player player){


		Street street = GameState.getInstance().getCheatManager().getCheatedStreet();
				// expose turned out to be true
				if(GameState.getInstance().getCheatManager().getLatestCheater() != null){

					Player cheater = GameState.getInstance().getCheatManager().getLatestCheater();
					int balanceCheater = cheater.getBalance();
					int balanceExposer = player.getBalance();
					//cheater.removeDeedFromPlayer(GameState.getInstance().getCheatManager().getCheatedStreet());
					GameState.getInstance().getCheatManager().getCheatedStreet().setOwner(null);

					cheater.setBalance(balanceCheater - 300);
					cheater.setBalance(balanceCheater - street.getPrice());
					player.setBalance(balanceExposer + 300);

					GameState.getInstance().updatePlayer(player);
					GameState.getInstance().updatePlayer(cheater);

					AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
					dialog.setTitle("You are right!");
					//dialog.setMessage("The cheater looses the cheated street and pays you $300");
					dialog.setMessage("The cheater pays a fine to the bank and pays $300 to you");
					dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which){
							dialog.dismiss();
						}
					});
					dialog.show();

				}
				else{
					int newBalance = player.getBalance() - 300;
					player.setBalance(newBalance);
					GameState.getInstance().updatePlayer(player);

					AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
					dialog.setTitle("Wrong accusation!");
					dialog.setMessage("The other player did not cheat.. -$300");
					dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which){
							dialog.dismiss();
						}
					});
					dialog.show();


				}

				GameState.getInstance().getCheatManager().flushCheater();
				showDifference(oldBalance, player.getBalance());

	}

	/**
	 * Cheating gives the player a street for free.
	 * if there was a cheater before, he gets overwritten
	 * and current player is now saved as latest cheater.
	 * CheatedStreet is saved for exposing feature
	 * Cheating ends a turn
	 *
	 * @param player cheating player
	 * @param street Street which the cheating player want to "acquire"
	 */
	public void cheat(Player player, Street street){

		if(street.getOwner()!=player){
			GameState.getInstance().getCheatManager().flushCheater();
			GameState.getInstance().getCheatManager().setLatestCheater(player);
			GameState.getInstance().getCheatManager().setCheatedStreet(street);

			GameState.getInstance().getDeedManager().cheatStreet(street, player);
			GameState.getInstance().updatePlayer(player);

			playerFinishedTurn();

			Toast.makeText(this, "You now own " + street.getName(), Toast.LENGTH_SHORT).show();
		}

	}
	public void checkGameOver(Player player){
		int balance = player.getBalance();
		if (balance <= 0){
			AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
			dialog.setTitle("Game Over");
			dialog.setMessage("You are out of funds!");
			dialog.setCanceledOnTouchOutside(false);
			dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which){
					addUp(player);
				}
			});
			dialog.show();
		}
	}

	public void addUp (final Player player){
		int balance = player.getBalance();
		int deedTotal = 0;
		int houseTotal = 0;
		int hotelTotal = 0;

		for (int i = 0; i < GameState.getInstance().getAllDeeds().size(); i++){
			if(GameState.getInstance().getAllDeeds().get(i) instanceof Street){
				Street street = (Street) GameState.getInstance().getAllDeeds().get(i);
				if (street.getOwner() != null && street.getOwner().getAddress().equals(player.getAddress()) && street.getOwner().getPort() == player.getPort()){
					int temp = street.getPrice();
					deedTotal += temp;
					if (street.getHouseCount() != 0){
						int temp2;
						int cost = street.getHousePrice();
						int houseamount = street.getHouseCount();
						temp2 = cost * houseamount;
						houseTotal += temp2;
					}
					if (street.getHasHotel()){
						int temp3 = street.getHotelPrice();
						hotelTotal += temp3;
					}
				}
			}else if(GameState.getInstance().getAllDeeds().get(i) instanceof Railroad){
				Railroad railroad = (Railroad) GameState.getInstance().getAllDeeds().get(i);
				if(railroad.getOwner() != null && railroad.getOwner().getAddress().equals(player.getAddress()) && railroad.getOwner().getPort() == player.getPort()){
					int temp4 = railroad.getPrice();
					deedTotal += temp4;
				}
			}else if(GameState.getInstance().getAllDeeds().get(i) instanceof Utility){
				Utility utility = (Utility) GameState.getInstance().getAllDeeds().get(i);
				if (utility.getOwner() != null && utility.getOwner().getAddress().equals(player.getAddress()) && utility.getOwner().getPort() == player.getPort()){
					int temp5 = utility.getPrice();
					deedTotal += temp5;
				}
			}
		}

		int total = balance + deedTotal + houseTotal + hotelTotal;

		String s1 = "Total Balance: " + balance;
		String s2 = "Total Deedvalue: " + deedTotal;
		String s3 = "Total Housevalue: " + houseTotal;
		String s4 = "Total Hotelvalue: " + hotelTotal;
		String s5 = "Total Belongings: " + total;
		AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
		dialog.setTitle("Summary");
		dialog.setMessage(s1 +"\n"+ s2 +"\n"+ s3 +"\n"+ s4 +"\n"+ s5);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}

	/**
	 * Gets name of selected gamepiece of a player and assigns it
	 * to an imageview initialized above.
	 * Playericon gets visible because they are all invisible after setup
	 * @param player Player whose position gets updated
	 */
	public void setPlayerIcon(Player player){

		String pieceName = player.getSelectedPiece().getName();
		int pos = player.getCurrentPosition();

		Log.d("POSITION: ", Integer.toString(pos));
		Log.d("PIECENAME: ", pieceName);


		switch (pieceName){
			case "Boat":
				boat.setX(fields[pos].getX());
				boat.setY(fields[pos].getY());
				boat.setVisibility(View.VISIBLE);

				break;

			case "Car":
				car.setX(fields[pos].getX());
				car.setY(fields[pos].getY());
				car.setVisibility(View.VISIBLE);

				break;

			case "Cat":
				cat.setX(fields[pos].getX());
				cat.setY(fields[pos].getY());
				cat.setVisibility(View.VISIBLE);

				break;

			case "Dino":
				dino.setX(fields[pos].getX());
				dino.setY(fields[pos].getY());
				dino.setVisibility(View.VISIBLE);

				break;

			case "Dog":
				dog.setX(fields[pos].getX());
				dog.setY(fields[pos].getY());
				dog.setVisibility(View.VISIBLE);

				break;

			case "Duck":
				duck.setX(fields[pos].getX());
				duck.setY(fields[pos].getY());
				duck.setVisibility(View.VISIBLE);

				break;

			case "Hat":
				hat.setX(fields[pos].getX());
				hat.setY(fields[pos].getY());
				hat.setVisibility(View.VISIBLE);

				break;

			case "Penguin":
				penguin.setX(fields[pos].getX());
				penguin.setY(fields[pos].getY());
				penguin.setVisibility(View.VISIBLE);

				break;

			default:
				break;

		}

	}

	/**
	 * Iterates through every player and calls setPlayerIcon to update positions
	 */
	public void setPlayerIcons(Player p){
		for (Player player:GameState.getInstance().getPlayers()) {
			if(player != p){
			setPlayerIcon(player);
		}
		}
	}

	public int getOldBalance(){
		return oldBalance;
	}

	public void setOldBalance(int oldBalance){
		this.oldBalance = oldBalance;
	}
}

