package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Board {
    private ArrayList<int[]> foundation1 = new ArrayList<>();
    private ArrayList<int[]> foundation2 = new ArrayList<>();
    private ArrayList<int[]> foundation3 = new ArrayList<>();
    private ArrayList<int[]> foundation4 = new ArrayList<>();

    private ArrayList<int[]> pile1 = new ArrayList<>();
    private ArrayList<int[]> pile2 = new ArrayList<>();
    private ArrayList<int[]> pile3 = new ArrayList<>();
    private ArrayList<int[]> pile4 = new ArrayList<>();
    private ArrayList<int[]> pile5 = new ArrayList<>();
    private ArrayList<int[]> pile6 = new ArrayList<>();
    private ArrayList<int[]> pile7 = new ArrayList<>();

    private ArrayList<int[]> discardPile = new ArrayList<>();

    Board(){
        this.shuffleAndDeal();

    }

    void setFoundation1(int[] card){

    }

    void setFoundation2(int[] card){

    }

    void setFoundation3(int[] card){

    }

    void setFoundation4(int[] card){

    }

    ArrayList<int[]> getFoundation1(){
        return this.foundation1;
    }


    private void shuffleAndDeal(){
        Deck mainDeck = new Deck();

        List<int[]> shuffledDeck = Arrays.asList(mainDeck.getDeck());
        Collections.shuffle(shuffledDeck);

        int deckIndex = 0;

        for (int i = 0; i < 7; i++){
            pile7.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }

        for (int i = 0; i < 6; i++){
            pile6.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }

        for (int i = 0; i < 5; i++){
            pile5.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }

        for (int i = 0; i < 4; i++){
            pile4.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }

        for (int i = 0; i < 5; i++){
            pile3.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }

        for (int i = 0; i < 2; i++){
            pile2.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }

        pile1.add(shuffledDeck.get(deckIndex));
        deckIndex++;

        while (deckIndex < 52){
            discardPile.add(shuffledDeck.get(deckIndex));
            deckIndex++;
        }
    }

    public static void main(String[] args){
        Board testBoard = new Board();

    }

}
