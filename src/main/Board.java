package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Board {

    private DeckPile deck = new DeckPile();

    private TablePile pile1 = new TablePile(1, deck);
    private TablePile pile2 = new TablePile(2, deck);
    private TablePile pile3 = new TablePile(3, deck);
    private TablePile pile4 = new TablePile(4, deck);
    private TablePile pile5 = new TablePile(5, deck);
    private TablePile pile6 = new TablePile(6, deck);
    private TablePile pile7 = new TablePile(7, deck);

    private DiscardPile discard = new DiscardPile();

    Board(){
        for (Card card: deck.getCardList()){
            discard.addCard(card);
        }

    }




    public static void main(String[] args){
        Board testBoard = new Board();

        System.out.println(testBoard.deck.getCardList());
        System.out.println(testBoard.pile3.getCardList());
        System.out.println(testBoard.discard.getCardList());
        System.out.println(testBoard.pile1.getCardList());
        System.out.println(testBoard.pile2.getCardList());

    }

}
