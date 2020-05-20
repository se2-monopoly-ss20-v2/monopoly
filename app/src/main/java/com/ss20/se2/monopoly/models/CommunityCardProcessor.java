package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.Card;

public class CommunityCardProcessor {


    public void performAction(Player player, Card card){

        int pos = player.getCurrentPosition();

        int temp = -400;

        switch (card.getName()){
            case "1":
                player.addCardToPlayer(card);
                break;

            case "2":
                player.setCurrentPosition(6);
                break;

            case "3":
                player.setCurrentPosition(pos-3);
                break;

            case "4":
                player.setCurrentPosition(5);
                break;

            case "5":
                player.updateBalance(-15);
                break;

            case "6":
                player.updateBalance(50);
                break;

            case "7":
                player.setCurrentPosition(26);
                break;

            case "8":
                player.updateBalance(100);
                break;

            case "9":
                player.setCurrentPosition(10);
                break;

            case "10":
                player.setCurrentPosition(15);
                break;

            case "11":
                player.setCurrentPosition(0);
                break;

            case "12":
                player.updateBalance(10);
                break;

            case "13":
                player.setCurrentPosition(18);
                break;

            case "14":
                player.updateBalance(-150);
                break;

            case "15":
                player.updateBalance(temp);
                break;

            case "16":
                player.updateBalance(temp);
                break;
        }
    }
}
