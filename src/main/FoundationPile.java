package main;

public class FoundationPile extends CardPile {

    FoundationPile(int ID) {
        setID("F" + ID);
    }
    FoundationPile(int ID, CardPile pile){
        setID("F"+ID);
        super.addCard(pile.getCardList());
    }

    @Override
    public boolean canAccept(Card aCard) {
        assert aCard != null;

        if (this.empty() && aCard.isAce()) {
            return true;
        } else if (!this.empty() && ! aCard.isAce()) {
            return (aCard.getSuit() == this.top().getSuit()) && (aCard.getNumber() == this.top().getNumber() + 1);
        } else if (this.empty() && !aCard.isAce()) {
            return false;
        } else{
            return false;
        }
    }
}

