package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    
    public ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<Card>();

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                deck.add(new Card(i, j));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public int getDeckSize(){
        return deck.size();
    }

    public Card takeCard(Deck deck) {
        if (deck.getDeckSize() == 0) {
            deck = new Deck();
            deck.shuffle();
        }
        Card card = deck.deck.get(0);
        deck.deck.remove(0);
        return card;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : deck) {
            result.append(card.toString()).append("\n");
        }
        return result.toString();
    }
}