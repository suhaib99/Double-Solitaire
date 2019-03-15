package main;

import java.util.ArrayList;

public class TablePile extends CardPile {

    TablePile(int numCards, DeckPile deck){
        ArrayList<Card> cards = deck.select(numCards);

        super.addCard(cards);

        if (!this.empty())
            this.top().flip();
    }

    @Override
    void addCard(ArrayList<Card> aCardList){
        for (Card card: aCardList){
            card.setX(this.getX());
            card.setY(this.getY() + (this.getNoCards()) * MainVisual.STACKVOFFSET);
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
    boolean contains(double mouseX, double mouseY){
        if (this.getNoCards() == 1){
            super.contains(mouseX, mouseY);
        } else if (!this.empty()){

            int counter1 = this.select().size();
            int counter;
            if (counter1 != 0)
                counter = this.getNoCards() - counter1;
            else
                counter = 0;

            return (mouseX > this.getCardList().get(counter).getX() && mouseX < this.getCardList().get(counter).getX()
                    + Card.WIDTH && mouseY > this.getCardList().get(counter).getY() && mouseY < this.getCardList()
                    .get(counter).getY() + MainVisual.STACKVOFFSET*counter);
        }
        return false;
    }

    boolean hoveringOverPile(double x, double y){
        if (!this.empty())
            return (x > this.getX() && x < this.getX() + Card.WIDTH && y > this.getY() && y < this.getY() + Card.HEIGHT
                    + (this.getNoCards() - 1)*MainVisual.STACKVOFFSET);

        return super.contains(x, y);
    }

    @Override
    boolean canAccept(ArrayList<Card> aCardList) {
        if (this.empty())
            return aCardList.get(0).isKing();

        if (!this.top().getFaceUp())
            return false;

        return (this.top().getColor() != aCardList.get(0).getColor()) && (this.top().getNumber() == aCardList.get(0)
                .getNumber() + 1);
    }

    void topDown(){
        if (!this.top().getFaceUp()){
            this.top().flip();
        }
    }


}
