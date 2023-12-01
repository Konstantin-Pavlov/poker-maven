package org.example;

import org.example.cards.Card;
import org.example.enums.Rank;
import org.example.enums.Suit;
import org.example.hand.Hand;
import org.example.helper.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();
        Hand hand = new Hand();
        hand.dealingCards();
        hand.showHand();
        helper.askToChangeCards(hand);
        hand.showHand();


/*        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.HEARTS, Rank.SIX),
                new Card(Suit.TILES, Rank.FIVE),
                new Card(Suit.PIKES, Rank.FIVE),
                new Card(Suit.CLOVES, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.KING))
        );

        Map<Rank, Long> map = cards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        System.out.println(map);
        System.out.print(map.containsValue(3L));*/
    }
}