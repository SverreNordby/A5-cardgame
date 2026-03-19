package edu.ntnu.idatt2003.cardgame;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandOfCardsTest {

    @Test
    void sumOfFaces_correctSum() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 1),
                new PlayingCard('S', 5),
                new PlayingCard('D', 10),
                new PlayingCard('C', 3),
                new PlayingCard('H', 7)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertEquals(26, hand.sumOfFaces());
    }

    @Test
    void sumOfFaces_singleCard() {
        HandOfCards hand = new HandOfCards(List.of(new PlayingCard('C', 13)));
        assertEquals(13, hand.sumOfFaces());
    }

    @Test
    void sumOfFaces_allAces() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 1),
                new PlayingCard('S', 1),
                new PlayingCard('D', 1),
                new PlayingCard('C', 1)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertEquals(4, hand.sumOfFaces());
    }

    @Test
    void heartsAsString_returnsHeartCards() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 12),
                new PlayingCard('S', 5),
                new PlayingCard('H', 9),
                new PlayingCard('D', 3),
                new PlayingCard('H', 1)
        );
        HandOfCards hand = new HandOfCards(cards);
        String result = hand.heartsAsString();
        assertTrue(result.contains("H12"));
        assertTrue(result.contains("H9"));
        assertTrue(result.contains("H1"));
    }

    @Test
    void heartsAsString_noHeartsReturnsNoHearts() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('S', 2),
                new PlayingCard('D', 5),
                new PlayingCard('C', 7)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertEquals("No Hearts", hand.heartsAsString());
    }

    @Test
    void heartsAsString_allHeartsDoesNotContainNoHearts() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 1),
                new PlayingCard('H', 2),
                new PlayingCard('H', 3)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertNotEquals("No Hearts", hand.heartsAsString());
    }

    @Test
    void hasQueenOfSpades_trueWhenPresent() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('S', 12),
                new PlayingCard('H', 3)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertTrue(hand.hasQueenOfSpades());
    }

    @Test
    void hasQueenOfSpades_falseWhenAbsent() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 12),
                new PlayingCard('S', 11)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertFalse(hand.hasQueenOfSpades());
    }

    @Test
    void isFlush_trueForFiveOfSameSuit() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 1),
                new PlayingCard('H', 5),
                new PlayingCard('H', 7),
                new PlayingCard('H', 9),
                new PlayingCard('H', 11)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertTrue(hand.isFlush());
    }

    @Test
    void isFlush_trueForAllSpades() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('S', 2),
                new PlayingCard('S', 4),
                new PlayingCard('S', 6),
                new PlayingCard('S', 8),
                new PlayingCard('S', 10)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertTrue(hand.isFlush());
    }

    @Test
    void isFlush_falseForMixedSuits() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 1),
                new PlayingCard('S', 5),
                new PlayingCard('H', 7),
                new PlayingCard('H', 9),
                new PlayingCard('H', 11)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertFalse(hand.isFlush());
    }

    @Test
    void isFlush_falseWhenNotFiveCards() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 1),
                new PlayingCard('H', 5),
                new PlayingCard('H', 7)
        );
        HandOfCards hand = new HandOfCards(cards);
        assertFalse(hand.isFlush());
    }

    @Test
    void getHandAsString_containsAllCards() {
        List<PlayingCard> cards = List.of(
                new PlayingCard('H', 4),
                new PlayingCard('S', 12)
        );
        HandOfCards hand = new HandOfCards(cards);
        String result = hand.getHandAsString();
        assertTrue(result.contains("H4"));
        assertTrue(result.contains("S12"));
    }
}