package main;

public class Deck {
    private Card[] deck = new Card[52];

    Deck(){
        int index = 0;
        for (int i = 1; i <= 13; i++){
            for(int j = 1; j <= 4; j++){
                deck[index] = new Card(j, i);
                index++;
            }
        }
    }
}
