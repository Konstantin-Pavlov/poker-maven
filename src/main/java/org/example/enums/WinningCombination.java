package org.example.enums;

import org.example.cards.Card;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum WinningCombination {

    FLASH_ROYAL {
        @Override
        public int getPriority() {
            return 1;
        }

        // 4 карты одного ранга
        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) && areAllTheSameSuitInHand(handCards) && areTheCardsInARow(handCards, true);
        }
    },
    STREET_FLASH {
        @Override
        public int getPriority() {
            return 2;
        }

        // 4 карты одного ранга
        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) && areAllTheSameSuitInHand(handCards) && areTheCardsInARow(handCards, false);
        }
    },
    CARE {
        @Override
        public int getPriority() {
            return 3;
        }

        // 4 карты одного ранга
        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) &&
                    numberOfSameRankInHand(handCards, 4, false, false, false, false);
        }
    },
    FULL_HOUSE {
        @Override
        public int getPriority() {
            return 4;
        }

        // пять любых карт одной масти (suit)
        @Override
        public boolean check(List<Card> handCards) {
            // в методе numberOfSameRankInHand арг 4 не важен в этом случае, случай fullHouse проверяется отдельно в if
            return validHandCards(handCards) &&
                    numberOfSameRankInHand(handCards, 4, true, false, false, false);
        }
    },
    FLASH {
        @Override
        public int getPriority() {
            return 5;
        }

        // пять любых карт одной масти (suit)
        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) && areAllTheSameSuitInHand(handCards);
        }
    },
    STREET {
        @Override
        public int getPriority() {
            return 6;
        }

        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) && areTheCardsInARow(handCards, false);
        }
    },
    THREE_OF_A_KIND {
        @Override
        public int getPriority() {
            return 7;
        }

        //  Тройка. 3 карты одного ранга
        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) &&
                    numberOfSameRankInHand(handCards, 3, false, true, false, false);
        }
    },
    TWO_PAIRS {
        @Override
        public int getPriority() {
            return 8;
        }

        // две пары. две пары карт одинаковых по рангу
        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) &&
                    numberOfSameRankInHand(handCards, 2, false, false, true, false);
        }
    },
    ONE_PAIR {
        @Override
        public int getPriority() {
            return 9;
        }

        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) &&
                    numberOfSameRankInHand(handCards, 2, false, false, false, true);
        }
    },
    HIGH_CARD {
        @Override
        public int getPriority() {
            return 10;
        }

        @Override
        public boolean check(List<Card> handCards) {
            return validHandCards(handCards) && findHighCard( handCards);
        }
    };

    public abstract int getPriority();

    public abstract boolean check(List<Card> handCards);

    private static boolean validHandCards(List<Card> handCards) {
        if (handCards.isEmpty()) {
            System.err.println("card list is empty");
            return false;
        }
        if (handCards.contains(null)) {
            System.err.println("card list contains null");
            return false;
        }
        if (handCards.size() != 5) {
            System.err.println("card list length is not 5");
            return false;
        }
        return true;
    }

    private static boolean areAllTheSameSuitInHand(List<Card> handCards) {
        return handCards.stream().allMatch(card -> card.getSuit() == handCards.getFirst().getSuit());
    }

    private static boolean numberOfSameRankInHand(List<Card> handCards,
                                                  long numberOfRanksToCheck,
                                                  boolean fullHouseCase,
                                                  boolean threeCardsCase,
                                                  boolean twoPairCase,
                                                  boolean onePairCase) {

        // получается мапа например такого вида - {SIX=1, KING=1, FIVE=3} (ранг и сколько раз он встречается)
        Map<Rank, Long> map = handCards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        //long l = map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(1L);

        // три карты одного ранга и две другого ранга
        // например {FOUR=2, FIVE=3}, то есть размер map - 2, так же в значениях должно быть 3 и 2 (иначе мб {FOUR=4, FIVE=1})
        if (fullHouseCase) {
            return map.size() == 2 && map.containsValue(3L) && map.containsValue(2L);
        }

        // три карты одного ранга
        // например {FOUR=1, SIX=1, FIVE=3}, то есть размер map - 3, так же в значениях должно быть 3 и 1
        if (threeCardsCase) {
            return map.size() == 3 && map.containsValue(3L) && map.containsValue(1L);
        }

        // две пары карт одинаковых по рангу
        // например {FOUR=2, SIX=1, FIVE=2}, то есть размер map - 3, так же в значениях должно быть 2 и 1
        if (twoPairCase) {
            return map.size() == 3 && map.containsValue(2L) && map.containsValue(1L);
        }

        // две карты одинаковых по рангу
        // например {FOUR=2, SIX=1, FIVE=1, TEN=1}, то есть размер map - 4, так же в значениях должно быть 2 и 1
        if (onePairCase) {
            return map.size() == 4 && map.containsValue(2L) && map.containsValue(1L);
        }

        return map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(1L).equals(numberOfRanksToCheck);
    }

    private static boolean areTheCardsInARow(List<Card> handCards, boolean flashRoyalCase) {
        // Сортируем список по рангу карты
        handCards.sort(Comparator.comparing(Card::getRank));

        // если первая карта в отсортированной руке не десятка, то это не Royal flash
        if (flashRoyalCase && !handCards.getFirst().getRank().equals(Rank.TEN)) {
            return false;
        }

        // Проверяем, что разница между рангами каждой карты и следующей карты равна 1
       /*     for (int i = 0; i < handCards.size() - 1; i++) {
                int currentRank = handCards.get(i).getEnumRank().ordinal();
                int nextRank = handCards.get(i + 1).getEnumRank().ordinal();
                if (nextRank - currentRank != 1) {
                    return false; // Если разница в ранге не равна 1, то это не стрит
                }
                return true;
            }*/

        // Если все карты имеют разницу в ранге, равную 1, то они идут подряд
        return IntStream.range(0, handCards.size() - 1)
                .allMatch(i -> handCards.get(i + 1).getRank().ordinal() - handCards.get(i).getRank().ordinal() == 1);
    }

    private static boolean findHighCard(List<Card> handCards){
        // сортировка по рангу
        handCards.sort(Comparator.comparing(Card::getRank));
        // если нашлась карта больше десятки, значит есть старшая карта
        return handCards.getLast().getRank().getPriority() > 10;
    }
}
