package main;

import java.util.ArrayList;

public class CardPile {
    private ArrayList<Card> cardList = new ArrayList<>();

    ArrayList<Card> getCardList(){
        return new ArrayList<>(cardList);
    }

    boolean empty(){
        return cardList.isEmpty();
    }

    Card top(){
        if (!empty())
            return cardList.get(cardList.size()-1);

        else
            return null;
    }

    Card pop() {
        if (!empty()) {
            Card return_val = this.top();
            cardList.remove(cardList.size() - 1);
            return return_val;
        }
        else
            return null;

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

    void addCard(Card card){
        cardList.add(card);
    }

    int getNoCards(){
        return cardList.size();
    }

    boolean canAccept(Card aCard){
        // Overridden
        return false;
    }

    public static void main(String[] args){
        CardPile testpile = new CardPile();
        testpile.addCard(new Card(1, 4));
        testpile.addCard(new Card(2, 5));

        System.out.println(testpile.getCardList().toString());
        System.out.println(testpile.getCardList().get(0).getNumber());
        System.out.println(testpile.getCardList().get(1).getNumber());
        System.out.println(testpile.pop());
        System.out.println(testpile.getCardList().toString());


    }

}
