package org.example.hand;

import org.example.cards.Card;
import org.example.deck.Deck;

public class Hand {
    public Card[] handCards = new Card[5];
    Deck deck = new Deck();

    public void dealingCards() {
        deck.genereitNewDeck();
        int counter = 0;
        while (counter < 5) {
            Card card = deck.getRandomCard();
            if (card.isAvailable()) {
                handCards[counter] = card;
                card.setAvailable(false);
                counter++;
            }
        }
    }

    public void changeCards(String[] cardNumbers) {
        System.out.print("Будут заменены карты с номерами: ");
        for (String cardNumber : cardNumbers) {
            System.out.print(cardNumber + " ");
            while (true) {
                Card card = deck.getRandomCard();
                if (card.isAvailable()) {
                    deck.returnCard(handCards[Integer.parseInt(cardNumber) - 1]);
                    handCards[Integer.parseInt(cardNumber) - 1] = card;
                    card.setAvailable(false);
                    break;
                }
            }
        }
        System.out.println();
    }

    public void showHand() {
        String fmt = " %-7s |";
        StringBuilder sb = new StringBuilder();
        StringBuilder header = new StringBuilder();
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < handCards.length; i++) {
            header.append(String.format(fmt, i + 1));
            value.append(String.format(fmt, handCards[i]));
        }

        sb.append(header.substring(0, header.length() - 1));
        sb.append("\n");
        sb.append(value.substring(0, value.length() - 1));
        System.out.println(sb);
    }
}
