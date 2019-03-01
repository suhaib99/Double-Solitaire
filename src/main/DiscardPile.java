package main;

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

}
