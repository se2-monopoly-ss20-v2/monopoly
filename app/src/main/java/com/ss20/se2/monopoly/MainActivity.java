package com.ss20.se2.monopoly;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.ss20.se2.monopoly.models.Dice;
import com.ss20.se2.monopoly.models.GamePiece;
import com.ss20.se2.monopoly.models.Gameboard;

public class MainActivity extends AppCompatActivity {

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
