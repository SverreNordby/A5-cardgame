package edu.ntnu.idatt2003.cardgame;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a hand of playing cards and provides analysis methods using streams.
 */
public class HandOfCards {

    private final List<PlayingCard> cards;

    /**
     * Creates a hand from the given list of cards.
     *
     * @param cards the cards in the hand
     */
    public HandOfCards(List<PlayingCard> cards) {
        this.cards = List.copyOf(cards);
    }

    /**
     * Returns the cards in the hand.
     *
     * @return unmodifiable list of cards
     */
    public List<PlayingCard> getCards() {
        return cards;
    }

    /**
     * Returns the sum of all face values in the hand (Ace = 1).
     *
     * @return sum of face values
     */
    public int sumOfFaces() {
        return cards.stream()
                .mapToInt(PlayingCard::getFace)
                .sum();
    }

    /**
     * Returns a formatted string of all heart cards, e.g. "H4 H9 H12".
     * Returns "No Hearts" if there are no heart cards in the hand.
     *
     * @return formatted string of heart cards, or "No Hearts"
     */
    public String heartsAsString() {
        String hearts = cards.stream()
                .filter(c -> c.getSuit() == 'H')
                .map(PlayingCard::getAsString)
                .collect(Collectors.joining(" "));
        return hearts.isEmpty() ? "No Hearts" : hearts;
    }

    /**
     * Checks whether the Queen of Spades (S12) is in the hand.
     *
     * @return true if the Queen of Spades is present
     */
    public boolean hasQueenOfSpades() {
        return cards.stream()
                .anyMatch(c -> c.getSuit() == 'S' && c.getFace() == 12);
    }

    /**
     * Checks whether the hand is a 5-flush (all 5 cards of the same suit).
     * Only meaningful when the hand contains exactly 5 cards.
     *
     * @return true if all cards share the same suit
     */
    public boolean isFlush() {
        if (cards.size() != 5) {
            return false;
        }
        char firstSuit = cards.get(0).getSuit();
        return cards.stream().allMatch(c -> c.getSuit() == firstSuit);
    }

    /**
     * Returns all cards as a space-separated string, e.g. "H4 S12 D7".
     *
     * @return string representation of the hand
     */
    public String getHandAsString() {
        return cards.stream()
                .map(PlayingCard::getAsString)
                .collect(Collectors.joining(" "));
    }
}
