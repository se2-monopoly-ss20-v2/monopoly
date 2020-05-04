package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;

import java.util.Collections;
import java.util.Stack;

public class CommunityCardDeck extends CardDeck {

    Stack<CommunityCard> stackOfCards = new Stack<>();

    CommunityCard card1 = new CommunityCard("1", "Gehe in das Gefängnis! Begib dich direkt dorthin. Gehe nicht über Los. Ziehe nicht 200€ ein. ");
    CommunityCard card2 = new CommunityCard("2", "Es ist dein Geburtstag. Ziehe von jedem Spieler 10€ ein.");
    CommunityCard card3 = new CommunityCard("3", "Aus Lagerverkäufen erhältst du 50€.");
    CommunityCard card4 = new CommunityCard("4", "Aus Lagerverkäufen erhältst du 50€.");
    CommunityCard card5 = new CommunityCard("5", "Zahle deine Versicherungsprämie 50€.");
    CommunityCard card6 = new CommunityCard("6", "Arzt-Kosten. Zahle 50€.");
    CommunityCard card7 = new CommunityCard("7", "Zahle an das Krankenhaus 100€.");
    CommunityCard card8 = new CommunityCard("8", "Bank-Irrtum zu deinen Gunsten. Ziehe 200€ ein.");
    CommunityCard card9 = new CommunityCard("9", "Gehe zurück zur Esterhazystraße. ");
    CommunityCard card10 = new CommunityCard("10", "Gehe zurück zur Esterhazystraße. ");
    CommunityCard card11 = new CommunityCard("11", "Zahle eine Strafe von 10€ oder nimm eine Ereigniskarte.");
    CommunityCard card12 = new CommunityCard("12", "Einkommensteuer-Rückzahlung. Ziehe 20€ ein.");
    CommunityCard card13 = new CommunityCard("13", "Du hast den 2.Preis in einer Schönheitskonkurrenz gewonnen. Ziehe 10€ ein. ");
    CommunityCard card14 = new CommunityCard("14", "Du kommst aus dem Gefängnis frei. (Diese Karte muss behalten werden, bis sie gebraucht oder verkauft wird)");
    CommunityCard card15 = new CommunityCard("15", "Du erbst 100€.");
    CommunityCard card16 = new CommunityCard("16", "2. Rücke vor bis auf Los.");

    CommunityCard[] cards = new CommunityCard[]{card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12,card13,card14,card15,card16};

    public void initializeDeck(){
        for (int i = 0; i < cards.length; i++) {
        }
        Collections.shuffle(stackOfCards);
    }

    public CommunityCard getNextCard() {
        if (!stackOfCards.isEmpty()) {
            return stackOfCards.pop();
        } else initializeDeck();
        return stackOfCards.pop();
    }
}


