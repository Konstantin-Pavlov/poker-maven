package org.example;

import org.example.enums.WinningCombination;
import org.example.hand.Hand;
import org.example.helper.Helper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();
        Hand hand = new Hand();
        hand.dealingCards();
        hand.showHand();
        helper.askToChangeCards(hand);
        hand.showHand();
        WinningCombination combination = Arrays.stream(WinningCombination.values())
                .sorted(Comparator.comparing(WinningCombination::getPriority))
                .filter(x -> x.check(Arrays.asList(hand.handCards)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("combination not found for cards in hand" + Arrays.toString(hand.handCards)));
        System.out.println(combination);


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