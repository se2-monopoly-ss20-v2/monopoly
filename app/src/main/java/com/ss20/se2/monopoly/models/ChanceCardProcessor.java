package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.Card;

public class ChanceCardProcessor {

    /**
     * Performs an action according to card name
     *
     * @param player Player that draws a chancecard
     * @param card Card that was drawn
     */
    public void performAction(Player player, Card card){

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
                player.updateBalance(balance+100);
                break;

            default:
                break;
        }
    }
}
