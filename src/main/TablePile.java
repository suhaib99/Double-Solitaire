package main;

import java.util.ArrayList;

public class TablePile extends CardPile {

    TablePile(int numCards, DeckPile deck){
        ArrayList<Card> cards = deck.select(numCards);

        setID("T"+numCards);

        super.addCard(cards);

        if (!this.empty())
            this.top().flip();
    }

    TablePile(int numCards, CardPile cardPile){

        super.addCard(cardPile.getCardList());

        setID("T"+numCards);

        if (!this.empty() && !this.top().getFaceUp())
            this.top().flip();
    }

    @Override
    void addCard(ArrayList<Card> aCardList){
        for (Card card: aCardList){
            card.setHeld(false);
        }

        this.getCardList().addAll(aCardList);
    }

    @Override
    ArrayList<Card> select() {
        ArrayList<Card> tempList = new ArrayList<>();

        if (!this.empty()) {
            if (!this.top().getFaceUp()) {
                this.top().flip();
                return tempList;
            }
            else {
                for (int i = 0; i < this.getNoCards(); i++) {
                    if (this.getCardList().get(i).getFaceUp()) {
                        tempList.add(this.getCardList().get(i));
                    }
                }
            }
        }
        return tempList;
    }

    @Override
    public boolean canAccept(Card card) {
        assert card != null;

        if (this.empty())
            return card.isKing();

        if (!this.top().getFaceUp())
            return false;

        return (this.top().getColor() != card.getColor()) && (this.top().getNumber() == card.getNumber() + 1);
    }

    void topDown(){
        if (!this.top().getFaceUp()){
            this.top().flip();
        }
    }


}
