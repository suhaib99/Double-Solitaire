package main;

import Gui.Transfer;
import com.sun.tools.javac.util.Name;
import javafx.scene.image.Image;
import sun.tools.jconsole.Tab;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Board {

    private DeckPile deck = new DeckPile();

    private TablePile[] tablePiles;
    private FoundationPile[] foundationPiles;

    private int team;

    private DiscardPile discard = new DiscardPile();

    private final ArrayList<GameListeners> Listeners = new ArrayList<>();

    private boolean load = false;

    public Board(String[] values){
        for (int i = 0; i < values.length; i++){

        }
    }

    public Board(int team) {

        int cardWidth = Card.WIDTH;
        int cardHeight = Card.HEIGHT;

        tablePiles = new TablePile[7];
        foundationPiles = new FoundationPile[4];

        this.team = team;


            for (int i = 0; i < 7; i++) {
                int j = i + 1;

                tablePiles[i] = new TablePile(j, deck);

            }


            for (int i = 0; i < 4; i++) {
                foundationPiles[i] = new FoundationPile(i + 1);

            }


            discard.addCard(deck.getCardList());

            notifyListeners();
        }



    public void move(CardPile origin, Card card, CardPile pileTo) {
        if (origin instanceof TablePile && pileTo instanceof TablePile){
            moveTableToTable(origin, card, pileTo);
        } else {
            absorbCard(origin);
            if (pileTo instanceof FoundationPile){
                pileTo.addCard(card);

            } else {
                assert pileTo instanceof TablePile;
                pileTo.addCard(card);

            }
        }
        if (!origin.empty() && !origin.top().getFaceUp())
            origin.top().flip();

        notifyListeners();

    }

    private void moveTableToTable(CardPile origin, Card card, CardPile pileTo){
        assert origin != null && card != null && pileTo != null;
        assert !origin.empty();
        Stack<Card> temp = new Stack<>();
        Card aCard = origin.pop();
        temp.push(aCard);
        while (!aCard.toString().equals(card.toString())){
            aCard = origin.pop();
            temp.push(aCard);
        }
        while (!temp.empty()){
            pileTo.addCard(temp.pop());
        }
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    private void absorbCard(CardPile source){
        if (source instanceof DiscardPile){
            assert !source.empty();
            source.pop();
        } else if (source instanceof FoundationPile){
            assert !source.empty();
            source.pop();
        } else {
            assert source instanceof TablePile;
            assert !source.empty();
            source.pop();
        }
    }

    public void flipThroughDiscard() {
        discard.flipThrough();
        notifyListeners();
    }

    public boolean gameOver() {
        if (!discard.empty())
            return false;

        for (int i = 1; i <= 7; i++) {
            if (!tablePiles[i - 1].empty()) {
                return false;
            }
        }
        return true;
    }

    public void addListener(GameListeners listener){
        assert listener != null;
        Listeners.add(listener);
    }

    private void notifyListeners(){
        for (GameListeners listeners: Listeners){
            listeners.gameStateChanged();
        }
    }

    public DiscardPile getDiscard() {
        return discard;
    }

    public FoundationPile[] getFoundationPiles() {
        return foundationPiles;
    }

    public TablePile[] getTablePiles() {
        return tablePiles;
    }

    public CardPile[] allPiles(){
        CardPile[] cardPiles = new CardPile[12];
        cardPiles[0] = getDiscard();
        for (int i = 1; (i-1) < this.getTablePiles().length; i++){
            cardPiles[i] = getTablePiles()[i-1];
        }

        for (int i = 8; (i-8) < getFoundationPiles().length; i++){
            cardPiles[i] = getFoundationPiles()[i-8];
        }

        return cardPiles;
    }

    public int getTeam() {
        return team;
    }

    public CardPile getCardPile(String ID){
        CardPile[] allPiles = allPiles();

        for (int i = 0; i < allPiles.length; i++){
            if (ID.equals(allPiles[i].getID())){
                return allPiles[i];
            }
        }
        return null;
    }

    public Card getCard(String cardString){
        CardPile[] allPiles = allPiles();
        for (int i = 0; i < allPiles.length; i++){
            for (int j = 0; j < allPiles[i].getNoCards(); j++){
                if (cardString.equals(allPiles[i].getCardList().get(j).toString())){
                    Card card = new Card(allPiles[i].getCardList().get(j));
                    notifyListeners();
                    return card;
                }
            }
        }
        return null;
    }

    public CardPile getSubStack(Card card, CardPile cardPile) {
        CardPile subCardPile = new CardPile();
        subCardPile.setID(cardPile.getID());
        ArrayList<Card> cardList = new ArrayList<>();

        ArrayList<Card> cardPileList = cardPile.getCardList();

        boolean select = false;

        for (Card card1: cardPileList){
            if (card1 == card){
                select = true;
            }
            if (select){
                cardList.add(card1);
            }

        }

        subCardPile.addCard(cardList);

        return subCardPile;
    }

    public static void main(String[] args){
        Board testBoard = new Board(1);

        System.out.println(testBoard.deck.getCardList());
        System.out.println(testBoard.tablePiles[0].top().getSuit());
        System.out.println(testBoard.discard.getCardList());
        System.out.println(testBoard.tablePiles[4].getCardList());
        System.out.println(testBoard.tablePiles[6].getCardList());

    }

}
