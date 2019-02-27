package main;

/**
 * Class containing Suit and Value of a playing card
 */
public class Card {
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
        this.faceUp = ! this.faceUp;
    }

    public static void main(String[] args){

    }
}
