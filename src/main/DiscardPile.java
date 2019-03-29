package main;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class DiscardPile extends CardPile {

    DiscardPile(){
        setID("D");

        if (!this.empty() && !this.top().getFaceUp())
            this.top().flip();
    }

    void flipThrough(){
        if(!this.empty()){

            if(this.top().getFaceUp()){
                this.top().flip();
            }

            this.addToFront(this.pop());
            this.top().flip();
        }

    }
/*
    @Override
    ArrayList<Image> display(int team){
        ArrayList<Image> images = new ArrayList<>();
        for (Card card:this.getCardList()){
            images.add(card.displayCard(team));
        }
        return images;
    }
    */

    @Override
    ArrayList<Card> select(){
        ArrayList<Card> select = new ArrayList<>();
        select.add(this.top());
        return select;
    }
}
