package com.ss20.se2.monopoly.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ss20.se2.monopoly.DeedManager;
import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.GameTile;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.models.fields.deeds.Utility;
import com.ss20.se2.monopoly.view.playerdeeds.PlayersDeedsFragment;

import java.util.ArrayList;

public class GameboardActivity extends AppCompatActivity{

	Button button_rollDice;
	TextView view_numberDice;
	TextView view_position;
	TextView view_balance;
	Button showDeeds;
	View playersDeedsFragment;

	Dice dice = new Dice();
	Gameboard gameboard;
	DeedManager deedManager;

	int amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_old2);

		final Player p = new Player("Wutzi", 1000, new GamePiece("shoe"), 0);

		gameboard = new Gameboard(getApplicationContext());
		gameboard.gameboardArray[0] = new GamePiece("Player 1");
		button_rollDice = findViewById(R.id.button_roll_dice);
		view_numberDice = findViewById(R.id.view_number_dice);
		view_position = findViewById(R.id.number_playerposition);
		view_balance = findViewById(R.id.text_balance);
		view_balance.setText("Balance: " + p.getBalance());
		showDeeds = findViewById(R.id.buttonShowDeeds);
		deedManager = new DeedManager(gameboard);

		button_rollDice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				amount = dice.roll();

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

				p.setCurrentPosition(gameboard.getPosition("Player 1"));
				checkPlayersPosition(p);

				view_numberDice.setText(Integer.toString(amount));
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
				AlertDialog dialog = new AlertDialog.Builder(GameboardActivity.this).create();
				dialog.setTitle(getString(R.string.buyDeedTitle));
				dialog.setMessage(getString(R.string.buyDeed, street.getName(), street.getPrice()));
				dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no), new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
						//TODO: START AUCTION - OR NOT
					}
				});
				dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						int balance = deedManager.performAcquiringDeed(street, player);
						view_balance.setText("Balance: " + balance);
					}
				});

				dialog.show();
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
						view_balance.setText("Balance: " + balance);
					}
				});



			} else {
				//hostile owns it.
			}


		}else if (currentTile instanceof Railroad) {

		}else if (currentTile instanceof Utility) {

		}
	}
}

