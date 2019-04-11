package main;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class containing Suit and Value of a playing card
 */
public class Card extends Object{

    private static final String[] NAMES = {"Ace", "2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    private static final String[] SUITS = {"spade", "club", "heart", "diamond"};
    // 1: Spade, 2: Club, 3: Hearts, 4: Diamond
    private int suit;

    // Ace: 1, Jack: 11, Queen: 12, King: 13
    private int rank;

    public static final int WIDTH = 75;
    public static final int HEIGHT = 113;

    private boolean faceUp;

    private boolean isHeld = false;

    // 0: Red, 1: Black
    private int color;

    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false;

        if (this.suit == 1 || this.suit == 2){
            this.color = 1;

        } else if (this.suit == 3 || this.suit == 4){
            this.color = 0;
        }
    }

    public Card(Card copyCard){
        this.suit = copyCard.getSuit();
        this.rank = copyCard.getNumber();
        this.faceUp = copyCard.getFaceUp();
        this.color = copyCard.getColor();
    }

    public boolean getFaceUp(){
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

    public boolean getHeld(){
        return isHeld;
    }

    public void flip(){
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

    public void setHeld(boolean held) {
        isHeld = held;
    }

    @Override
    public String toString() {
        return getSuit() + ":" + getNumber();
    }

    // team 1: blue, team 2: red
    public Image displayCard(int team){
        Image cardImage;

        try {
            if (this.faceUp) {
                cardImage = new Image(new FileInputStream(String.format("Double-Solitaire/src/res/Decks/pngs/%s",
                        this.getSuitName() + this.getName() + ".png")));
                return cardImage;

            } else if (!this.getFaceUp()){
                return getBackInstance(team);
                }

            } catch(java.io.FileNotFoundException e) {
                System.out.println("FileNotFound");
            }
        return null;
        }

    public Image getBackInstance(int team) {
        Image cardImage;

        try {
            if (team == 1) {
                cardImage = new Image(new FileInputStream("Double-Solitaire/src/res/Decks/pngs/blueBack.png"));
                return cardImage;
            } else if (team == 2) {
                cardImage = new Image(new FileInputStream("Double-Solitaire/src/res/Decks/pngs/redBack.png"));
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        return null;
    }

    public static void main(String[] args){


    }
}
