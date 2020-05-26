package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.Card;

public class ChanceCardProcessor {

    public void performAction(Player player, Card card){
        
        int pos = player.getCurrentPosition();
        int balance = player.getBalance();

        int temp = 400;

        switch (card.getName()){
            case "FREEJAIL":
                player.addCardToPlayer(card);
                break;

            case "SHOPPING":
                player.updateBalance(balance-300);
                break;

            case "DENTIST":
                player.updateBalance(balance-200);
                break;

            case "COFFEE":
                player.updateBalance(balance-100);
                break;

            case "DAYOFF":
                player.updateBalance(balance-50);
                break;

            case "PARKING":
                player.updateBalance(balance-25);
                break;

            case "KICKSTART":
                player.updateBalance(balance+200);
                break;

            case "8":
                player.updateBalance(balance+100);
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
                player.updateBalance(balance+10);
                break;

            case "13":
                player.setCurrentPosition(18);
                break;

            case "14":
                player.updateBalance(balance-150);
                break;

            case "15":
                player.updateBalance(balance-temp);
                break;

            case "16":
                player.updateBalance(balance-temp);
                break;

            default:
                break;
        }
    }
}
