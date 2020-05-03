package com.ss20.se2.monopoly.models;

import com.ss20.se2.monopoly.models.fields.cards.ChanceCard;

import java.util.Collections;
import java.util.Stack;

public class ChanceCardDeck {

    Stack<ChanceCard> stackOfCards = new Stack<>();


    ChanceCard card1 = new ChanceCard("1", "Du kommst aus dem Gefängnis frei. (Diese Karte muss behalten werden, bis sie gebraucht oder verkauft wird)");
    ChanceCard card2 = new ChanceCard("2", "Gehe zum nächsten Bahnhof und zahle dem Eigentümer die doppelte Miete. Wenn der Bahnhof niemanden gehört, darfst du ihn von der Bank kaufen.");
    ChanceCard card3 = new ChanceCard("3", "Gehe 3 Felder zurück.");
    ChanceCard card4 = new ChanceCard("4", "Mache einen Ausflug zum Südbahnhof. Wenn du über Los kommst, ziehe 200€ ein.");
    ChanceCard card5 = new ChanceCard("5", "Strafe für zu schnelles Fahren. 15€.");
    ChanceCard card6 = new ChanceCard("6", "Die Bank zahlt dir eine Dividende von 50€.");
    ChanceCard card7 = new ChanceCard("7", "Rücke vor bis zur Hellbrunner Straße: Wenn du über Los kommst, ziehe 200€ ein. ");
    ChanceCard card8 = new ChanceCard("8", "Du hast in einem Kreuzworträtsel-Wettbewerb gewonnen und bekommst 100€.");
    ChanceCard card9 = new ChanceCard("9", "Gehe in das Gefängnis! Begib dich direkt dorthin. Gehe nicht über Los. Ziehe nicht 200€ ein.");
    ChanceCard card10 = new ChanceCard("10", "Rücke vor bis zur Rheinstraße.");
    ChanceCard card11 = new ChanceCard("11", "Rücke vor bis auf Los. ");
    ChanceCard card12 = new ChanceCard("12", "Miete und Anleihezinsen werden fällig. Die Bank zahlt dir 150€.");
    ChanceCard card13 = new ChanceCard("13", "Rücke vor bis zur Wiener Straße. Wenn du über Los kommst, ziehe 200€ ein. ");
    ChanceCard card14 = new ChanceCard("14", "Zahle Schulgeld 150€.");
    ChanceCard card15 = new ChanceCard("15", "Straßenreparaturen. Zahle für jedes Haus in deinem Besitz 40€ und für jedes Hotel 115€ an die Bank.");
    ChanceCard card16 = new ChanceCard("16", "Lasse alle deine Häuser renovieren. Zahle an die Bank für jedes haus 25€ und für jedes Hotel 100€. ");

    public void initializeDeck(){
        stackOfCards.push(card1);
        stackOfCards.push(card2);
        stackOfCards.push(card3);
        stackOfCards.push(card4);
        stackOfCards.push(card5);
        stackOfCards.push(card6);
        stackOfCards.push(card7);
        stackOfCards.push(card8);
        stackOfCards.push(card9);
        stackOfCards.push(card10);
        stackOfCards.push(card11);
        stackOfCards.push(card12);
        stackOfCards.push(card13);
        stackOfCards.push(card14);
        stackOfCards.push(card15);
        stackOfCards.push(card16);

        Collections.shuffle(stackOfCards);
    }

    public ChanceCard getNextCard() {
        if (!stackOfCards.isEmpty()) {
            return stackOfCards.pop();
        } else initializeDeck();
        return stackOfCards.pop();
    }


}
