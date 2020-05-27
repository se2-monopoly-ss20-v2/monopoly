package com.ss20.se2.monopoly.view;

import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;
import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.view.deed.DeedFragmentDelegate;
import com.ss20.se2.monopoly.view.dialog.DialogContainerFragment;

public class GameboardActivity extends AppCompatActivity implements DeedFragmentDelegate{

	Button button_rollDice;
	TextView view_numberDice;
	TextView view_position;
	TextView view_balance;
	Button showDeeds;
	View playersDeedsFragment;
	TextView updateBalance;

	Dice dice = new Dice();
	Gameboard gameboard;
	DeedManager deedManager;
	ChanceCardDeck chanceCards;
	CommunityCardDeck communityCards;
	ChanceCardProcessor chanceCardProcessor;
	CommunityCardProcessor communityCardProcessor;
	int oldBalance;

	int amount;

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

		final Player p = new Player("Wutzi", 1000, new GamePiece("shoe"), 0);

		chanceCards = new ChanceCardDeck();
		communityCards = new CommunityCardDeck();
		chanceCards.initializeDeck();
		communityCards.initializeDeck();
		chanceCardProcessor = new ChanceCardProcessor();
		communityCardProcessor = new CommunityCardProcessor();

		gameboard = new Gameboard(getApplicationContext());
		gameboard.gameboardArray[0] = new GamePiece("Player 1");
		button_rollDice = findViewById(R.id.button_roll_dice);
		view_numberDice = findViewById(R.id.view_number_dice);
		view_position = findViewById(R.id.number_playerposition);
		view_balance = findViewById(R.id.text_balance);
		view_balance.setText(getString(R.string.balance,  p.getBalance()));
		showDeeds = findViewById(R.id.buttonShowDeeds);
		deedManager = new DeedManager(gameboard);
		updateBalance = findViewById(R.id.changeOfBalance);


		button_rollDice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				amount = dice.roll();
				setOldBalance(p.getBalance());

				for (int i = 0; i < amount; i++) {

					if (gameboard.getPosition("Player 1") - amount + i == 40) {
						i = 0;
					}

					int x = gameboard.moveUI("Player 1", i);

					float xaxis = findViewById(R.id.playericon).getX();
					float yaxis = findViewById(R.id.playericon).getY();


					switch (x) {
						case 1: {
							findViewById(R.id.playericon).setX(xaxis - 72);
							break;
						}
						case 2: {
							findViewById(R.id.playericon).setY(yaxis - 72);
							break;
						}
						case 3: {
							findViewById(R.id.playericon).setX(xaxis + 72);
							break;
						}
						case 4: {
							findViewById(R.id.playericon).setY(yaxis + 72);
							break;
						}
						default:
							break;
					}

				}
				gameboard.move("Player 1", amount);

				updateBalance.setText("");
				p.setCurrentPosition(gameboard.getPosition("Player 1"));
				checkPlayersPosition(p);

				view_numberDice.setText(Integer.toString(amount));
				view_position.setText(Integer.toString(gameboard.getPosition("Player 1")));

				findViewById(R.id.playericon).setX(fields[gameboard.getPosition("Player 1")].getX());
				findViewById(R.id.playericon).setY(fields[gameboard.getPosition("Player 1")].getY());
			}
		});
	}

	public void checkPlayersPosition(final Player player) {
		GameTile currentTile = gameboard.gameTiles.get(player.getCurrentPosition());

		//i know not that performant, but currently i only want to proof our concept.
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
					}
				});

				dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						int balance = deedManager.performAcquiringHouseFor(street, player);
						view_balance.setText(getString(R.string.balance,  balance));
						showDifference(getOldBalance(), player.getBalance());
					}
				});



			}
		}else if (currentTile instanceof CommunityCard){
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

	@Override
	public void performAcquiringDeed(Street street, Player player){
		int newBalance = deedManager.performAcquiringDeed(street, player);
		player.updateBalance(newBalance);
		view_balance.setText(getString(R.string.balance,  newBalance));
		Toast.makeText(this, "You now own " + street.getName(), Toast.LENGTH_SHORT).show();
		showDifference(getOldBalance(), player.getBalance());

	}
	public void showDifference(int oldBalance, int newBalance){
		int difference = oldBalance-newBalance;

		if (difference == 0){
			updateBalance.setText("");
		}
		else if (oldBalance<newBalance){
			//set Color
			updateBalance.setText("+$" + difference);
		}
		else {
			updateBalance.setText("-$" + difference);
		}

	}

	public int getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(int oldBalance) {
		this.oldBalance = oldBalance;
	}
}

