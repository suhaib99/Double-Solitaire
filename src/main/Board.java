package main;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Board {

    protected DeckPile deck = new DeckPile();

    protected TablePile[] tablePiles;
    protected FoundationPile[] foundationPiles;


    protected DiscardPile discard = new DiscardPile();

    Board(){
        tablePiles = new TablePile[7];

        for (int i = 0; i < 7; i++){
            int j = i+1;

            tablePiles[i] = new TablePile(j, deck);

        }

        foundationPiles = new FoundationPile[4];

        for (int i = 0; i < 4; i++){
            foundationPiles[i] = new FoundationPile();
        }

        for (Card card: deck.getCardList()){
            discard.addCard(card);
        }
    }

    void move(CardPile pileFrom, CardPile pileTo){
        if (pileTo.canAccept(pileFrom.top())){
            pileTo.addCard(pileFrom.pop());
        }
    }

    void flipThroughDiscard(){
        discard.flipThrough();
    }

    boolean gameOver(){
        if (!discard.empty())
            return false;

        for (int i = 1; i<=7; i++){
            if (!tablePiles[i-1].empty()){
                return false;
            }
        }
        System.out.println("You Win!");
        return true;
    }

    void display(){




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
        Board testBoard = new Board();

        System.out.println(testBoard.deck.getCardList());
        System.out.println(testBoard.tablePiles[0].top().getSuit());
        System.out.println(testBoard.discard.getCardList());
        System.out.println(testBoard.tablePiles[4].getCardList());
        System.out.println(testBoard.tablePiles[6].getCardList());

    }

}
