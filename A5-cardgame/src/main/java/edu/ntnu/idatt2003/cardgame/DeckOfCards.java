package edu.ntnu.idatt2003.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a full deck of 52 playing cards.
 * The deck contains all combinations of 4 suits and face values 1–13.
 */
public class DeckOfCards {

    private final char[] suits = {'S', 'H', 'D', 'C'};
    private final List<PlayingCard> deck;
    private final Random random;

    /**
     * Creates a full deck of 52 playing cards (all suits, faces 1–13).
     */
    public DeckOfCards() {
        deck = new ArrayList<>();
        random = new Random();
        for (char suit : suits) {
            for (int face = 1; face <= 13; face++) {
                deck.add(new PlayingCard(suit, face));
            }
        }
    }

    /**
     * Deals n random cards from the deck (without removing them).
     *
     * @param n number of cards to deal, between 1 and 52
     * @return a list of n randomly chosen cards
     * @throws IllegalArgumentException if n is out of range
     */
    public List<PlayingCard> dealHand(int n) {
        if (n < 1 || n > 52) {
            throw new IllegalArgumentException("n must be between 1 and 52");
        }
        List<PlayingCard> shuffled = new ArrayList<>(deck);
        Collections.shuffle(shuffled, random);
        return shuffled.subList(0, n);
    }
}
