package main;

import java.util.Arrays;

public class Deck {
    private int[][] deck = new int[52][2];

    Deck(){
        int index = 0;
        for (int i = 1; i <= 13; i++){
            for(int j = 1; j <= 4; j++){
                Card tempCard = new Card(j, i);
                deck[index] = tempCard.toArray();
                index++;
            }
        }
    }

    int[][] getDeck(){
        return deck;
    }

    public static void main(String[] args){
        Deck testDeck = new Deck();
        System.out.println(Arrays.toString(testDeck.getDeck()[10]));
        System.out.println(testDeck.getDeck()[10][1]);
    }
}
