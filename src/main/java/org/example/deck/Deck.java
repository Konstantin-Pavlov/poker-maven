package org.example.deck;

import java.util.Random;

import org.example.cards.Card;
import org.example.enums.Suit;
import org.example.enums.Rank;

public class Deck {
    private static Random random = new Random();
    private static Card[] cards;

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public void genereitNewDeck() {
        // счетчик для заполнения массива cards
        int cardCounter = 0;

        // создание массива с картами
        cards = new Card[52];

        // заполнение массива cards случайными картами
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards[cardCounter++] = new Card(suit, rank);
            }
        }

        shuffleArray(cards);
    }


    // используется алгоритм Фишера-Йейтса для случайного перемешивания элементов массива
    // тип Object (родитель всех остальных классов) поэтому в аргументах может быть любой тип
    public void shuffleArray(Object[] array) {
        int n = array.length;

        for (int i = n - 1; i > 0; i--) {
            // Генерируем случайный индекс от 0 до i (включительно)
            int j = (int) (Math.random() * (i + 1));

            // Обмениваем значения элементов с индексами i и j
            var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public Card getRandomCard() {
        int choice = random.nextInt(cards.length);
        return cards[choice];
    }

    public void returnCard(Card card) {
        card.setAvailable(true);
    }

}
