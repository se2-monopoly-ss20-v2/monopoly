package com.ss20.se2.monopoly.view;

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
import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.view.deed.DeedFragmentDelegate;
import com.ss20.se2.monopoly.view.dialog.DialogContainerFragment;

public class GameboardActivity extends AppCompatActivity implements DeedFragmentDelegate{

	Button button_rollDice;
	TextView view_numberDice;
	TextView view_numberDice2;
	TextView viewdoubles;
	TextView view_position;
	TextView view_balance;
	Button showDeeds;
	View playersDeedsFragment;
	TextView updateBalance;

	Dice dice = new Dice();
	Dice dice2 = new Dice();
	Gameboard gameboard;
	DeedManager deedManager;
	int oldBalance;

	int amount;
	int roll1;
	int roll2;
	int doublescounter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard_activity);

		final Player p = new Player("Wutzi", 1000, new GamePiece("shoe"), 0);

		gameboard = new Gameboard(getApplicationContext());
		gameboard.gameboardArray[0] = new GamePiece("Player 1");
		button_rollDice = findViewById(R.id.button_roll_dice);
		view_numberDice = findViewById(R.id.view_number_dice);
		view_numberDice2 = findViewById(R.id.view_number_dice2);
		viewdoubles = findViewById(R.id.doubles);
		view_position = findViewById(R.id.number_playerposition);
		view_balance = findViewById(R.id.text_balance);
		view_balance.setText(getString(R.string.balance,  p.getBalance()));
		showDeeds = findViewById(R.id.buttonShowDeeds);
		deedManager = new DeedManager(gameboard);
		updateBalance = findViewById(R.id.changeOfBalance);


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

				view_numberDice.setText("Roll 1: " + Integer.toString(roll1));
				view_numberDice2.setText("Roll 2: " + Integer.toString(roll2));
				view_position.setText(Integer.toString(gameboard.getPosition("Player 1")));

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

