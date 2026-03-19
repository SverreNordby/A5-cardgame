package edu.ntnu.idatt2003.cardgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayingCardTest {

    @Test
    void constructor_validCard_doesNotThrow() {
        assertDoesNotThrow(() -> new PlayingCard('H', 1));
    }

    @Test
    void constructor_invalidSuit_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PlayingCard('X', 5));
    }

    @Test
    void constructor_faceTooLow_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 0));
    }

    @Test
    void constructor_faceTooHigh_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 14));
    }

    @Test
    void getAsString_returnsCorrectFormat() {
        PlayingCard card = new PlayingCard('H', 4);
        assertEquals("H4", card.getAsString());
    }

    @Test
    void getAsString_kingOfSpades() {
        PlayingCard card = new PlayingCard('S', 13);
        assertEquals("S13", card.getAsString());
    }

    @Test
    void equals_sameCard_returnsTrue() {
        PlayingCard a = new PlayingCard('D', 7);
        PlayingCard b = new PlayingCard('D', 7);
        assertEquals(a, b);
    }

    @Test
    void equals_differentFace_returnsFalse() {
        PlayingCard a = new PlayingCard('D', 7);
        PlayingCard b = new PlayingCard('D', 8);
        assertNotEquals(a, b);
    }

    @Test
    void equals_differentSuit_returnsFalse() {
        PlayingCard a = new PlayingCard('D', 7);
        PlayingCard b = new PlayingCard('H', 7);
        assertNotEquals(a, b);
    }

    @Test
    void equals_null_returnsFalse() {
        PlayingCard card = new PlayingCard('C', 3);
        assertNotEquals(null, card);
    }

    @Test
    void hashCode_equalCards_haveSameHashCode() {
        PlayingCard a = new PlayingCard('S', 12);
        PlayingCard b = new PlayingCard('S', 12);
        assertEquals(a.hashCode(), b.hashCode());
    }
}