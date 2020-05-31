package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;

import java.util.Collections;
import java.util.Stack;

public class ChanceCardDeck {

    Stack<ChanceCard> stackOfCards = new Stack<>();

    ChanceCard card1 = new ChanceCard("FREEJAIL", "Money can buy everything. You get out of jail. (Card needs to be kept until played)");
    ChanceCard card2 = new ChanceCard("SHOPPING", "You go on a shopping trip. -$300");
    ChanceCard card3 = new ChanceCard("DENTIST", "You need a convincing smile in your business. -$200 for your dentist");
    ChanceCard card4 = new ChanceCard("COFFEE", "NEED COFFEE. You stockpile on coffee. -$100");
    ChanceCard card5 = new ChanceCard("DAYOFF", "During your day off, you lose some profit. -$50");
    ChanceCard card6 = new ChanceCard("PARKING", "You left your card in a no-parking zone. -$25");
    ChanceCard card7 = new ChanceCard("KICKSTART", "Someone donated $100 to your kickstart project. Of course you are spending it on a new TV");



    ChanceCard[] cards = new ChanceCard[]{card1,card2,card3,card4,card5,card6,card7};

    public void initializeDeck(){
        for (int i = 0; i < cards.length; i++) {
            stackOfCards.push(cards[i]);
        }
        Collections.shuffle(stackOfCards);
    }

    public ChanceCard getNextCard() {
        if (!stackOfCards.isEmpty()) {
            return stackOfCards.pop();
        } else initializeDeck();
        return stackOfCards.pop();
    }


}
