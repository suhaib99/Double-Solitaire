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

    private int getNumber() {
        return this.number;
    }

    private int getSuit() {
        return suit;
    }

    int[] toArray(){
        int[] suitNumber = new int[2];
        suitNumber[0] = getSuit();
        suitNumber[1] = getNumber();

        return suitNumber;
    }

    public static void main(String[] args){
        Card testCard = new Card(1, 1);
        System.out.println(testCard.toArray()[0]);
    }
}
