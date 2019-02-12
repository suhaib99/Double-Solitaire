package main;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> foundation1 = new ArrayList<>();
    private ArrayList<Card> foundation2 = new ArrayList<>();
    private ArrayList<Card> foundation3 = new ArrayList<>();
    private ArrayList<Card> foundation4 = new ArrayList<>();

    private ArrayList<Card> pile1 = new ArrayList<>();
    private ArrayList<Card> pile2 = new ArrayList<>();
    private ArrayList<Card> pile3 = new ArrayList<>();
    private ArrayList<Card> pile4 = new ArrayList<>();
    private ArrayList<Card> pile5 = new ArrayList<>();
    private ArrayList<Card> pile6 = new ArrayList<>();
    private ArrayList<Card> pile7 = new ArrayList<>();

    private ArrayList<Card> discardPile = new ArrayList<>();

    Board(){
        this.shuffle();

    }

    private void shuffle(){
        Deck mainDeck = new Deck();
    }

}
