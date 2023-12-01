import org.example.cards.Card;
import org.example.enums.Rank;
import org.example.enums.Suit;
import org.junit.Test;
import org.example.enums.WinningCombinations;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningCombinationsTest {

    @Test
    public void isFlashRoyal() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.CLOVES, Rank.ACE),
                new Card(Suit.CLOVES, Rank.QUEEN),
                new Card(Suit.CLOVES, Rank.JACK),
                new Card(Suit.CLOVES, Rank.KING),
                new Card(Suit.CLOVES, Rank.TEN))
        );
        Assert.assertTrue(WinningCombinations.FLASH_ROYAL.check(cards));
    }

    @Test
    public void isStreetFlash() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.FIVE),
                new Card(Suit.TILES, Rank.EIGHT),
                new Card(Suit.TILES, Rank.SEVEN),
                new Card(Suit.TILES, Rank.NINE),
                new Card(Suit.TILES, Rank.SIX))
        );
        Assert.assertTrue(WinningCombinations.STREET_FLASH.check(cards));
    }

    @Test
    public void isCare() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.QUEEN),
                new Card(Suit.PIKES, Rank.QUEEN),
                new Card(Suit.CLOVES, Rank.SIX),
                new Card(Suit.TILES, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.QUEEN))
        );
        Assert.assertTrue(WinningCombinations.CARE.check(cards));
    }

    @Test
    public void isFullHouse() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.CLOVES, Rank.TWO),
                new Card(Suit.PIKES, Rank.JACK),
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.TILES, Rank.JACK),
                new Card(Suit.PIKES, Rank.TWO))
        );
        Assert.assertTrue(WinningCombinations.FULL_HOUSE.check(cards));
    }

    @Test
    public void isFlash() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.CLOVES, Rank.FOUR),
                new Card(Suit.CLOVES, Rank.FIVE),
                new Card(Suit.CLOVES, Rank.SIX),
                new Card(Suit.CLOVES, Rank.SEVEN),
                new Card(Suit.CLOVES, Rank.EIGHT))
        );
        Assert.assertTrue(WinningCombinations.FLASH.check(cards));
    }

    @Test
    public void isStreet() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.SIX),
                new Card(Suit.PIKES, Rank.FIVE),
                new Card(Suit.CLOVES, Rank.FOUR),
                new Card(Suit.TILES, Rank.EIGHT),
                new Card(Suit.HEARTS, Rank.SEVEN))
        );
        Assert.assertTrue(WinningCombinations.STREET.check(cards));
    }

    @Test
    public void isThreeCards() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.SIX),
                new Card(Suit.PIKES, Rank.SEVEN),
                new Card(Suit.CLOVES, Rank.NINE),
                new Card(Suit.TILES, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.SEVEN))
        );
        Assert.assertTrue(WinningCombinations.THREE_OF_A_KIND.check(cards));
    }

    @Test
    public void isTwoPairs() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.THREE),
                new Card(Suit.PIKES, Rank.SEVEN),
                new Card(Suit.CLOVES, Rank.NINE),
                new Card(Suit.TILES, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.THREE))
        );
        Assert.assertTrue(WinningCombinations.TWO_PAIRS.check(cards));
    }

    @Test
    public void isOnePair() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.ACE),
                new Card(Suit.PIKES, Rank.KING),
                new Card(Suit.CLOVES, Rank.NINE),
                new Card(Suit.TILES, Rank.KING),
                new Card(Suit.HEARTS, Rank.THREE))
        );
        Assert.assertTrue(WinningCombinations.ONE_PAIR.check(cards));
    }

    @Test
    public void isHighCard() {
        List<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(Suit.TILES, Rank.JACK),
                new Card(Suit.PIKES, Rank.FIVE),
                new Card(Suit.CLOVES, Rank.NINE),
                new Card(Suit.TILES, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.THREE))
        );
        Assert.assertTrue(WinningCombinations.HIGH_CARD.check(cards));
    }
}