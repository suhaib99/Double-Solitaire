package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

/**
 * Class containing Suit and Value of a playing card
 */
public class Card {

    private static final String[] NAMES = {"Ace", "2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    private static final String[] SUITS = {"spade", "club", "heart", "diamond"};
    // 1: Spade, 2: Club, 3: Hearts, 4: Diamond
    private int suit;

    // Ace: 1, Jack: 11, Queen: 12, King: 13
    private int rank;

    public static final int WIDTH = 75;
    public static final int HEIGHT = 113;

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
    boolean isQueen(){
        return (getNumber() == 12);
    }
    boolean isJack(){
        return (getNumber() == 11);
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

    // team 1: blue, team 2: red
    public Image displayCard(int team){
        Image cardImage;

        try {
            if (this.faceUp) {
                cardImage = new Image(new FileInputStream(String.format("Double-Solitaire/src/res/Decks/pngs/%s", this.getSuitName() + this.getName() + ".png")));
                return cardImage;
            } else if (team == 1){
                cardImage = new Image(new FileInputStream("Double-Solitaire/src/res/Decks/pngs/blueBack"));
                return cardImage;
            } else if (team == 2){
                cardImage = new Image(new FileInputStream("Double-Solitaire/src/res/Decks/pngs/redBack"));
                return cardImage;
            }

        }
        catch(java.io.FileNotFoundException e) {
            System.out.println("FileNotFound");
        }
        return null;
    }

    public static void main(String[] args){


    }
}
