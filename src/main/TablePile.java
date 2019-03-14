package main;

import java.util.ArrayList;

public class TablePile extends CardPile {

    TablePile(int numCards, DeckPile deck){
        ArrayList<Card> cards = deck.select(numCards);
        for (Card card: cards){
            this.addCard(card);
        }
        if (!this.empty())
            this.top().flip();
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
                for (int i = this.getNoCards() - 1; i <= 0; i--) {
                    if (this.getCardList().get(i).getFaceUp()) {
                        tempList.add(this.pop());
                    }
                }
            }
        }
        return tempList;
    }

    @Override
    boolean contains(double mouseX, double mouseY){
        if (this.getNoCards() == 1 && !this.empty()){
            super.contains(mouseX, mouseY);
        } else if (!this.empty()){
            int counter = 0;
            for (int i = this.getNoCards() - 1; i <= 0; i--) {
                if (!this.getCardList().get(i).getFaceUp()){
                    break;
                }
                counter++;
            }
            int index = this.getNoCards() - counter;
            return (mouseX > this.getX() && mouseX < this.getX() + Card.WIDTH && mouseY > this.getY() +
            MainVisual.STACKVOFFSET*index && mouseY < this.getY() + MainVisual.STACKVOFFSET*index + Card.HEIGHT);
        }
        return false;
    }

    @Override
    boolean canAccept(Card aCard) {
        if (this.empty())
            return aCard.isKing();

        if (!this.top().getFaceUp())
            return false;

        return (this.top().getColor() != aCard.getColor()) && (this.top().getNumber() == aCard.getNumber() + 1);
    }


}
