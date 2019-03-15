package main;

import java.util.ArrayList;

public class FoundationPile extends CardPile {

    @Override
    boolean canAccept(ArrayList<Card> aCard){
        if (this.empty() && aCard.get(0).isAce()){
            return true;
        } else
        return (aCard.get(0).getSuit() == this.top().getSuit()) && (aCard.get(0).getNumber() == this.top().getNumber() - 1);
    }


}
