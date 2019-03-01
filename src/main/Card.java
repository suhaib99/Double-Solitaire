package main;

/**
 * Class containing Suit and Value of a playing card
 */
public class Card {

    private static final String[] NAMES = {"A", "2","3","4","5","6","7","8","9","10","J","Q","K"};
    private static final String[] SUITS = {"Spades", "Clubs", "Hearts", "Diamonds"};
    // 1: Spade, 2: Club, 3: Hearts, 4: Diamond
    private int suit;

    // Ace: 1, Jack: 11, Queen: 12, King: 13
    private int rank;

    private boolean faceUp;

    // 0: Red, 1: Black
    private int color;

    Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false;

        if (this.suit == 1 || this.suit == 2){
            this.color = 1;

        } else if (this.suit == 3 || this.suit == 4){
            this.color = 0;
        }
    }

    boolean getFaceUp(){
        return this.faceUp;
    }

    int getNumber() {
        return this.rank;
    }

    int getSuit() {
        return suit;
    }

    int getColor(){
        return color;
    }

    void flip(){
        this.faceUp = !this.faceUp;
    }

    boolean isKing(){
        return (getNumber() == 13);
    }

    boolean isAce(){
        return (getNumber() == 1);
    }

    String getName(){
        return NAMES[this.getNumber() - 1];
    }

    String getSuitName(){
        return SUITS[this.getSuit() - 1];
    }

    public static void main(String[] args){

    }
}
