package main;

import Gui.Transfer;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class CardPile {
    private ArrayList<Card> cardList = new ArrayList<>();
    private String ID;
    private int x;
    private int y;

    public ArrayList<Card> getCardList(){
        return new ArrayList<>(cardList);
    }

    public boolean empty(){
        return cardList.isEmpty();
    }

    public Card top(){
        if (!empty())
            return cardList.get(cardList.size() - 1);

        else
            return null;
    }

    public Card bottom(){
        assert !empty();
        return getCardList().get(0);
    }

    public Card pop() {
        if (!empty()) {
            Card return_val = this.top();
            cardList.remove(cardList.size() - 1);
            return return_val;
        } else
            return null;

    }

    int getIndex(Card card){
        return this.cardList.indexOf(card);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    ArrayList<Card> select(int end){
        //do nothing to be overridden
        return null;
    }

    ArrayList<Card> select(){
        //do nothing to be overriden in TablePile
        return null;
    }

    void addToFront(Card aCard){
        cardList.add(0, aCard);
    }

    void addCard(ArrayList<Card> card){
        cardList.addAll(card);
        for (Card card1: card) {
            card1.setX(this.getX());
            card1.setY(this.getY());
        }
    }

    void merge(Transfer cardPile){
        for (int i = 0; i < cardPile.size(); i++){
            addCard(cardPile.getArray()[i]);
        }

    }

    void addCard(Card card){
        cardList.add(card);
        card.setY(this.getX());
        card.setX(this.getY());
        card.setHeld(false);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*boolean contains(double mouseX, double mouseY){
        return (mouseX > this.x && mouseX < this.x + Card.WIDTH && mouseY > this.y && mouseY < this.y + Card.HEIGHT);
    }*/

    int getNoCards(){
        return cardList.size();
    }

    public boolean canAccept(Card aCard){
        // Overridden
        return false;
    }

    @Override
    public String toString() {
        return ID;
    }

    // team number is passed as argument
    /*void display(int team){
        getChildren().clear();

        int offsetMultiple = 0;

        for (Card card: this.cardList){
            
        }
        /*
        ArrayList<Image> images = new ArrayList<>();
        for (Card card: this.getCardList()){
            images.add(card.displayCard(team));
        }
        return images;

    }*/

    public static void main(String[] args){
        CardPile testpile = new CardPile();

        System.out.println(testpile.getCardList().toString());
        System.out.println(testpile.getCardList().get(0).getNumber());
        System.out.println(testpile.getCardList().get(1).getNumber());
        System.out.println(testpile.pop());
        System.out.println(testpile.getCardList().toString());


    }

}
