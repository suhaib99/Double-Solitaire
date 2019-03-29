package main;

import Gui.Transfer;
import com.sun.tools.javac.util.Name;
import javafx.scene.image.Image;
import sun.tools.jconsole.Tab;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Board {

    protected DeckPile deck = new DeckPile();

    protected TablePile[] tablePiles;
    protected FoundationPile[] foundationPiles;

    public int team;


    protected DiscardPile discard = new DiscardPile();

    private final ArrayList<GameListeners> Listeners = new ArrayList<>();

    public Board(int team) {

        int cardWidth = Card.WIDTH;
        int cardHeight = Card.HEIGHT;

        tablePiles = new TablePile[7];

        this.team = team;

        for (int i = 0; i < 7; i++) {
            int j = i + 1;

            tablePiles[i] = new TablePile(j, deck);

            tablePiles[i].setX(GameSinglePlayer.HOFFSET + i * (GameSinglePlayer.HOFFSET + cardWidth));
            tablePiles[i].setY(2 * GameSinglePlayer.VOFFSET + cardHeight);

            for (int n = 0; n < tablePiles[i].getNoCards(); n++) {
                Card card = tablePiles[i].getCardList().get(n);

                card.setX(GameSinglePlayer.HOFFSET + i * (GameSinglePlayer.HOFFSET + cardWidth));
                card.setY(2 * GameSinglePlayer.VOFFSET + cardHeight + n * GameSinglePlayer.STACKVOFFSET);

            }

        }

        foundationPiles = new FoundationPile[4];

        for (int i = 0; i < 4; i++) {
            foundationPiles[i] = new FoundationPile(i+1);

            foundationPiles[i].setX(4 * GameSinglePlayer.HOFFSET + 3 * cardWidth + i * (cardWidth+ GameSinglePlayer.HOFFSET));
            foundationPiles[i].setY(GameSinglePlayer.VOFFSET);
        }


        discard.addCard(deck.getCardList());

        discard.setX(GameSinglePlayer.HOFFSET);
        discard.setY(GameSinglePlayer.VOFFSET);

        for (int i = 0; i < discard.getNoCards(); i++){
            Card card1 = discard.getCardList().get(i);
            card1.setX(GameSinglePlayer.HOFFSET);
            card1.setY(GameSinglePlayer.VOFFSET);
        }

    }

    public void move(CardPile origin, Card card, CardPile pileTo) {
        if (origin instanceof TablePile && pileTo instanceof TablePile){
            moveTableToTable(origin, card, pileTo);
        } else {
            absorbCard(origin);
            if (pileTo instanceof FoundationPile){
                pileTo.addToFront(card);
            } else {
                assert pileTo instanceof TablePile;
                pileTo.addToFront(card);
            }
        }
        if (!origin.empty())
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

    void notifyListeners(){
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
        cardPiles[1] = getDiscard();
        for (int i = 0; i < this.getTablePiles().length; i++){
            cardPiles[i] = getTablePiles()[i];
        }

        for (int i = 7; (i-7) < getFoundationPiles().length; i++){
            cardPiles[i] = getFoundationPiles()[i-7];
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
    /**
     * discard at [0]
     * foundation at [1-4]
     * table piles at [5-11]
     * @return Image[]: array of images arraylists to display
     */
    void display(){
        /*
        ArrayList<Image>[] images = new ArrayList[12];

        images[0] = discard.display(this.team);

        for (int i = 1; i < 5; i++){
            if (!foundationPiles[i-1].empty())
                images[i] = foundationPiles[i-1].display(this.team);
            else{
                images[i] = null;
            }
        }

        for (int i = 5; i < 12; i++){
            images[i] = tablePiles[i-5].display(this.team);
        }
        return images;


        /*
        String[] foundationLetters = new String[4];
        for (int i = 0; i < foundationPiles.length; i++) {
            if (!foundationPiles[i].empty())
                foundationLetters[i] = foundationPiles[i].top().getName();
        }

        for (int i = 0; i < foundationLetters.length; i++){
            if (foundationLetters[i] == null){
                foundationLetters[i] = "[]";
            }
        }

        System.out.println("D\tF1 F2 F3 F4\n" + discard.top().getName() + "\t" + foundationLetters[0]
                + " " + foundationLetters[1] + foundationLetters[2]
                + " " + foundationLetters[3]
                + "\n"+ discard.top().getSuitName() +"\n-----------------\n\t1      2      3      4      5      6      7\n\t"
                + tablePiles[0].top().getName() + "      " + tablePiles[1].top().getName()
                + "       " + tablePiles[2].top().getName() + "      " + tablePiles[3].top().getName()
                + "      " +
                tablePiles[4].top().getName() + "       " + tablePiles[5].top().getName() + "      " +
                tablePiles[6].top().getName() + "\n\t" + tablePiles[0].top().getSuitName() + " " +
                tablePiles[1].top().getSuitName()+ " " + tablePiles[2].top().getSuitName() + " " +
                tablePiles[3].top().getSuitName() + " " + tablePiles[4].top().getSuitName() + " " +
                tablePiles[5].top().getSuitName() + " " + tablePiles[6].top().getSuitName() + "\n");
       */
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
