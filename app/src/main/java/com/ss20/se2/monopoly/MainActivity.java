package com.ss20.se2.monopoly;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        setContentView(R.layout.activity_main);

        final ImageView[] fields = initializeUI();



        gameboard.gameboardArray[0] = new GamePiece("Player 1");
        button_rollDice = findViewById(R.id.button_roll_dice);
        view_numberDice = findViewById(R.id.view_number_dice);
        view_position = findViewById(R.id.number_playerposition);

        button_rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = dice.roll();

/*                for (int i = 0; i < amount; i++) {

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

                }*/
                gameboard.move("Player 1", amount);

                view_numberDice.setText(Integer.toString(amount));
                view_position.setText(Integer.toString(gameboard.getPosition("Player 1")));


                findViewById(R.id.playericon).setX(fields[gameboard.getPosition("Player 1")].getX());
                findViewById(R.id.playericon).setY(fields[gameboard.getPosition("Player 1")].getY());


            }
        });
    }
}
