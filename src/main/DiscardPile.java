package main;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class DiscardPile extends CardPile {

    DiscardPile(){
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

    @Override
    ArrayList<Image> display(int team){
        ArrayList<Image> images = new ArrayList<>();
        images.add(this.top().displayCard(team));
        return images;
    }
}
