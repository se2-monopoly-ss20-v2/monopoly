package com.ss20.se2.monopoly;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.Gameboard;

public class MainActivity extends AppCompatActivity{

	Button button_rollDice;
	TextView view_numberDice;
	TextView view_position;
	Dice dice = new Dice();
	Gameboard gameboard = new Gameboard();
	int amount;
	int playerPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button_rollDice = findViewById(R.id.button_roll_dice);
		view_numberDice = findViewById(R.id.view_number_dice);
		view_position = findViewById(R.id.view_position);

		button_rollDice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				amount = dice.roll();
				view_numberDice.setText(Integer.toString(amount));
				gameboard.move("Player 1", amount);

				//TODO: Spielerposition darstellen



			}
		});

	}
}
