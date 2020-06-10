package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.Card;

public class CommunityCardProcessor {

    /**
     * Performs an action according to card name
     *
     * @param player Player that draws a communitycard
     * @param card Card that was drawn
     */
    public void performAction(Player player, Card card){

        int balance = player.getBalance();

        int temp = 400;

        switch (card.getName()){
            case "BIRTHDAY":
                player.setBalance(balance+50);
                break;

            case "LUCK":
                player.setBalance(balance+200);
                break;

            case "BADLUCK":
                player.setBalance(balance-150);
                break;

            case "DATE":
                player.setBalance(balance-15);
                break;

            case "STOCK":
                player.updateBalance(balance+300);
                break;

            case "HOSPITAL":
                player.updateBalance(balance-200);
                break;

            case "7":
                player.setCurrentPosition(26);
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
                player.updateBalance(balance+temp);
                break;

            case "16":
                player.updateBalance(balance+temp);
                break;

            default:
                break;
        }
    }
}
