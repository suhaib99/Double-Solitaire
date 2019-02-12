package main;

/**
 * Class containing Suit and Value of a playing card
 */
public class Card {
    // 1: Spade, 2: Club, 3: Hearts, 4: Diamond
    private int suit;

    // Ace: 1, Jack: 11, Queen: 12, King: 13
    private int number;

    Card(int suit, int number){
        this.suit = suit;
        this.number = number;
    }

    int getNumber() {
        return this.number;
    }

    int getSuit() {
        return suit;
    }
}
