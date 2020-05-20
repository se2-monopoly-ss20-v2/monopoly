package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.CommunityCard;

import java.util.Collections;
import java.util.Stack;

public class CommunityCardDeck {

    Stack<CommunityCard> stackOfCards = new Stack<>();

    CommunityCard card1 = new CommunityCard("JAIL", "Gehe in das Gefängnis! Begib dich direkt dorthin. Gehe nicht über Los. Ziehe nicht 200€ ein. ");
    CommunityCard card2 = new CommunityCard("BIRTHDAY", "Es ist dein Geburtstag. Ziehe von jedem Spieler 10€ ein.");
    CommunityCard card3 = new CommunityCard("SALE", "Aus Lagerverkäufen erhältst du 50€.");
    CommunityCard card4 = new CommunityCard("RENT", "Deine Jahresrente wird fällig, ziehe 100€ ein.");
    CommunityCard card5 = new CommunityCard("INSURANCE", "Zahle deine Versicherungsprämie 50€.");
    CommunityCard card6 = new CommunityCard("DOCTOR", "Arzt-Kosten. Zahle 50€.");
    CommunityCard card7 = new CommunityCard("HOSPITAL", "Zahle an das Krankenhaus 100€.");
    CommunityCard card8 = new CommunityCard("BANK", "Bank-Irrtum zu deinen Gunsten. Ziehe 200€ ein.");
    CommunityCard card9 = new CommunityCard("ESTERHAZY", "Gehe zurück zur Esterhazystraße. ");
    CommunityCard card10 = new CommunityCard("SHARES", ". Du erhältst auf Vorzugs-Aktien 7% Dividende 25€.");
    CommunityCard card11 = new CommunityCard("FINE", "Zahle eine Strafe von 10€ oder nimm eine Ereigniskarte.");
    CommunityCard card12 = new CommunityCard("TAXES", "Einkommensteuer-Rückzahlung. Ziehe 20€ ein.");
    CommunityCard card13 = new CommunityCard("BEAUTY", "Du hast den 2.Preis in einer Schönheitskonkurrenz gewonnen. Ziehe 10€ ein. ");
    CommunityCard card14 = new CommunityCard("FREEJAIL", "Du kommst aus dem Gefängnis frei. (Diese Karte muss behalten werden, bis sie gebraucht oder verkauft wird)");
    CommunityCard card15 = new CommunityCard("INHERIT", "Du erbst 100€.");
    CommunityCard card16 = new CommunityCard("GO", "Rücke vor bis auf Los.");

    CommunityCard[] cards = new CommunityCard[]{card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12,card13,card14,card15,card16};

    public void initializeDeck(){
        for (int i = 0; i < cards.length; i++) {
            stackOfCards.push(cards[i]);
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


