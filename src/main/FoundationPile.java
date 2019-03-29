package main;

public class FoundationPile extends CardPile {

    FoundationPile(int ID) {
        setID("F" + ID);
    }

    @Override
    public boolean canAccept(Card aCard) {
        assert aCard != null;

        if (this.empty() && aCard.isAce()) {
            return true;
        } else if (!this.empty()) {
            return (aCard.getSuit() == this.top().getSuit()) && (aCard.getNumber() == this.top().getNumber() - 1);
        } else if (this.empty() && !aCard.isAce()) {
            return false;
        } else{
            return false;
        }
    }
}

