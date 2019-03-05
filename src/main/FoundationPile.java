package main;

public class FoundationPile extends CardPile {

    @Override
    boolean canAccept(Card aCard){
        if (this.empty() && aCard.isAce()){
            return true;
        } else
        return (aCard.getSuit() == this.top().getSuit()) && (aCard.getNumber() == this.top().getNumber() - 1);
    }


}
