package edu.ntnu.idatt2003.cardgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckOfCardsTest {

    private DeckOfCards deck;

    @BeforeEach
    void setUp() {
        deck = new DeckOfCards();
    }

    @Test
    void dealHand_returnsCorrectNumberOfCards() {
        List<PlayingCard> hand = deck.dealHand(5);
        assertEquals(5, hand.size());
    }

    @Test
    void dealHand_allCardsAreUnique() {
        List<PlayingCard> hand = deck.dealHand(52);
        long distinct = hand.stream().distinct().count();
        assertEquals(52, distinct);
    }

    @Test
    void dealHand_throwsWhenNIsZero() {
        assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
    }

    @Test
    void dealHand_throwsWhenNExceeds52() {
        assertThrows(IllegalArgumentException.class, () -> deck.dealHand(53));
    }

    @Test
    void dealHand_singleCard() {
        List<PlayingCard> hand = deck.dealHand(1);
        assertEquals(1, hand.size());
    }
}
