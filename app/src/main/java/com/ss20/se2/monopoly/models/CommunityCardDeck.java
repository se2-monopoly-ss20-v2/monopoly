package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;

import java.util.Collections;
import java.util.Stack;

public class CommunityCardDeck {

    Stack<CommunityCard> stackOfCards = new Stack<>();

    CommunityCard card1 = new CommunityCard("BIRTHDAY", "It's your birthday, your parents give you $50");
    CommunityCard card2 = new CommunityCard("LUCK", "Today is your lucky-day, you find $200 on the street");
    CommunityCard card3 = new CommunityCard("BADLUCK","You lost your wallet, $150 gone...");
    CommunityCard card4 = new CommunityCard("DATE", "To impress your date you pay the bill. Thankfully you were eating fast-food. -15$");
    CommunityCard card5 = new CommunityCard("STOCK", "You invested in facemasks. $300 for you");
    CommunityCard card6 = new CommunityCard("HOSPITAL", "You fell of your chair while complaining about your employees. The hospital bill is $200");

    CommunityCard[] cards = new CommunityCard[]{card1,card2,card3,card4,card5,card6};

    /**
     * fills card deck with communitycards and shuffles them
     */
    public void initializeDeck(){
        for (int i = 0; i < cards.length; i++) {
            stackOfCards.push(cards[i]);
        }
        Collections.shuffle(stackOfCards);
    }

    /**
     *  Gets a new card out of the deck
     *  if the deck is empty, it will be
     *  initialized and shuffled again
     *
     * @return CommunityCard
     */
    public CommunityCard getNextCard() {
        if (!stackOfCards.isEmpty()) {
            return stackOfCards.pop();
        } else initializeDeck();
        return stackOfCards.pop();
    }
}


