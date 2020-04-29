package com.ss20.se2.monopoly.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;


import android.os.Bundle;

public class OldActivity extends AppCompatActivity{
	/*
		Button button_rollDice;
		TextView view_numberDice;
		TextView view_position;

		Dice dice = new Dice();
		Gameboard gameboard = new Gameboard();

		int amount;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			gameboard.gameboardArray[0] = new GamePiece("Player 1");
			button_rollDice = findViewById(R.id.button_roll_dice);
			view_numberDice = findViewById(R.id.view_number_dice);
			view_position = findViewById(R.id.number_playerposition);

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

					view_numberDice.setText(Integer.toString(amount));
					view_position.setText(Integer.toString(gameboard.getPosition("Player 1")));

				}
			});
		}
	}

	XML:

<?xml version="1.0" encoding="utf-8"?>

<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.ss20.se2.monopoly.view.OldActivity">
        <ImageView
                android:layout_width="0dp"
                app:srcCompat="@color/colorAccent"
                android:id="@+id/view_background"
                app:layout_constraintWidth_percent="0.58"
                android:layout_height="0dp"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent" app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent" app:layout_constraintVertical_bias="1.0"/>
        <ImageView
                android:layout_width="250dp"
                android:layout_height="250dp" app:srcCompat="@android:color/darker_gray"
                android:id="@+id/tile_middle"
                app:layout_constraintTop_toTopOf="parent" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toStartOf="@+id/view_background"
                app:layout_constraintBottom_toBottomOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@color/colorPrimaryDark"
                android:id="@+id/tile_1"
                app:layout_constraintStart_toEndOf="@+id/tile_2" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_0" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tile_5" android:adjustViewBounds="false"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/darker_gray"
                android:id="@+id/tile_10"
                app:layout_constraintStart_toStartOf="@+id/view_background" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintTop_toBottomOf="@+id/tile_middle" app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintVertical_bias="0.0"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/darker_gray"
                android:id="@+id/tile_20"
                app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintStart_toStartOf="@+id/view_background"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/darker_gray"
                android:id="@+id/tile_0"
                app:layout_constraintEnd_toEndOf="@+id/view_background" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/tile_middle"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/darker_gray"
                android:id="@+id/tile_30"
                app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_blue_light"
                android:id="@+id/tile_9"
                app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_8" app:layout_constraintStart_toEndOf="@+id/tile_10"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_blue_light"
                android:id="@+id/tile_8"
                app:layout_constraintStart_toEndOf="@+id/tile_9" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_7" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_7"
                app:layout_constraintStart_toEndOf="@+id/tile_8" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_6"
                app:layout_constraintBottom_toBottomOf="@+id/view_background"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_blue_light"
                android:id="@+id/tile_6"
                app:layout_constraintStart_toEndOf="@+id/tile_7" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_5"
                app:layout_constraintBottom_toBottomOf="@+id/view_background"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_5"
                app:layout_constraintStart_toEndOf="@+id/tile_6" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_4"
                app:layout_constraintBottom_toBottomOf="@+id/view_background"
                app:layout_constraintTop_toBottomOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_4"
                app:layout_constraintStart_toEndOf="@+id/tile_5" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_3" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@color/colorPrimaryDark"
                android:id="@+id/tile_3"
                app:layout_constraintStart_toEndOf="@+id/tile_4" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_2" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_2"
                app:layout_constraintStart_toEndOf="@+id/tile_3" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_1" app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tile_5"/>
        <Button
                android:text="roll dice!"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/button_roll_dice" app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintEnd_toEndOf="parent" app:layout_constraintStart_toEndOf="@+id/tile_30"/>
        <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:id="@+id/view_number_dice"
                app:layout_constraintTop_toBottomOf="@+id/button_roll_dice" app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toEndOf="@+id/view_background" android:textAlignment="center"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_purple"
                android:id="@+id/tile_13"
                app:layout_constraintTop_toBottomOf="@+id/tile_14"
                app:layout_constraintBottom_toTopOf="@+id/tile_12"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_15"
                app:layout_constraintTop_toBottomOf="@+id/tile_16"
                app:layout_constraintBottom_toTopOf="@+id/tile_14"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_purple"
                android:id="@+id/tile_14"
                app:layout_constraintTop_toBottomOf="@+id/tile_15"
                app:layout_constraintBottom_toTopOf="@+id/tile_13"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_17"
                app:layout_constraintTop_toBottomOf="@+id/tile_18"
                app:layout_constraintBottom_toTopOf="@+id/tile_16"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_orange_dark"
                android:id="@+id/tile_16"
                app:layout_constraintTop_toBottomOf="@+id/tile_17"
                app:layout_constraintBottom_toTopOf="@+id/tile_15"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_purple"
                android:id="@+id/tile_11"
                app:layout_constraintTop_toBottomOf="@+id/tile_12"
                app:layout_constraintBottom_toTopOf="@+id/tile_10" app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_orange_dark"
                android:id="@+id/tile_19"
                app:layout_constraintBottom_toTopOf="@+id/tile_18"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"
                app:layout_constraintTop_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_12"
                app:layout_constraintTop_toBottomOf="@+id/tile_13"
                app:layout_constraintBottom_toTopOf="@+id/tile_11"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_orange_dark"
                android:id="@+id/tile_18"
                app:layout_constraintTop_toBottomOf="@+id/tile_19"
                app:layout_constraintBottom_toTopOf="@+id/tile_17"
                app:layout_constraintEnd_toStartOf="@+id/tile_middle"
                app:layout_constraintStart_toStartOf="@+id/view_background"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_red_dark"
                android:id="@+id/tile_24"
                app:layout_constraintStart_toEndOf="@+id/tile_23" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_25" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_25"
                app:layout_constraintStart_toEndOf="@+id/tile_24" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_26" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_red_dark"
                android:id="@+id/tile_23"
                app:layout_constraintStart_toEndOf="@+id/tile_22" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_24" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_orange_light"
                android:id="@+id/tile_26"
                app:layout_constraintStart_toEndOf="@+id/tile_25" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_27" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_orange_light"
                android:id="@+id/tile_27"
                app:layout_constraintStart_toEndOf="@+id/tile_26" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_28" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_28"
                app:layout_constraintStart_toEndOf="@+id/tile_27" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_29" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_orange_light"
                android:id="@+id/tile_29"
                app:layout_constraintStart_toEndOf="@+id/tile_28" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_30" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_22"
                app:layout_constraintStart_toEndOf="@+id/tile_21" app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_23" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_red_dark"
                android:id="@+id/tile_21"
                app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintEnd_toStartOf="@+id/tile_22"
                app:layout_constraintStart_toEndOf="@+id/tile_20" app:layout_constraintTop_toTopOf="@+id/view_background"
                app:layout_constraintBottom_toTopOf="@+id/tile_middle"/>
        <TextView
                android:text="Playerposition:"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/text_playerposition"
                app:layout_constraintTop_toBottomOf="@+id/view_number_dice"
                app:layout_constraintStart_toEndOf="@+id/view_background" app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_green_dark"
                android:id="@+id/tile_32"
                app:layout_constraintTop_toBottomOf="@+id/tile_31"
                app:layout_constraintBottom_toTopOf="@+id/tile_33" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_green_dark"
                android:id="@+id/tile_31"
                app:layout_constraintBottom_toTopOf="@+id/tile_32"
                app:layout_constraintTop_toBottomOf="@+id/tile_30" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_33"
                app:layout_constraintTop_toBottomOf="@+id/tile_32"
                app:layout_constraintBottom_toTopOf="@+id/tile_34" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_green_dark"
                android:id="@+id/tile_34"
                app:layout_constraintTop_toBottomOf="@+id/tile_33"
                app:layout_constraintBottom_toTopOf="@+id/tile_35" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_35"
                app:layout_constraintTop_toBottomOf="@+id/tile_34"
                app:layout_constraintBottom_toTopOf="@+id/tile_36" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_36"
                app:layout_constraintTop_toBottomOf="@+id/tile_35"
                app:layout_constraintBottom_toTopOf="@+id/tile_37" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_blue_dark"
                android:id="@+id/tile_37"
                app:layout_constraintTop_toBottomOf="@+id/tile_36"
                app:layout_constraintBottom_toTopOf="@+id/tile_38" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/white"
                android:id="@+id/tile_38"
                app:layout_constraintTop_toBottomOf="@+id/tile_37"
                app:layout_constraintBottom_toTopOf="@+id/tile_39" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="0dp"
                android:layout_height="0dp" app:srcCompat="@android:color/holo_blue_dark"
                android:id="@+id/tile_39"
                app:layout_constraintTop_toBottomOf="@+id/tile_38"
                app:layout_constraintBottom_toTopOf="@+id/tile_0" app:layout_constraintEnd_toEndOf="@+id/view_background"
                app:layout_constraintStart_toEndOf="@+id/tile_middle"/>
        <ImageView
                android:layout_width="25dp"
                android:layout_height="21dp" app:srcCompat="@mipmap/ic_launcher"
                android:id="@+id/playericon"
                app:layout_constraintTop_toBottomOf="@+id/tile_39"
                app:layout_constraintStart_toStartOf="@+id/tile_0"/>
        <TextView
                android:text="TextView"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/number_playerposition"
                app:layout_constraintTop_toBottomOf="@+id/text_playerposition"
                app:layout_constraintStart_toEndOf="@+id/tile_35" app:layout_constraintEnd_toEndOf="parent"/>

-->
</androidx.constraintlayout.widget.ConstraintLayout>




	 */
}
