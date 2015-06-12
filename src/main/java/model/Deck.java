package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    
    public ArrayList<Card> cardArrayList;

    public Deck() {

        cardArrayList = new ArrayList<Card>();

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cardArrayList.add(new Card(i, j));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cardArrayList);
    }

    public int getDeckSize(){
        return cardArrayList.size();
    }

    public Card takeCard() {
        if (this.cardArrayList.size() == 0) {
            Deck deck = new Deck();
            deck.shuffle();
            this.cardArrayList = deck.getCardArrayList();
        }
        Card card = cardArrayList.get(0);
        cardArrayList.remove(0);
        return card;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : cardArrayList) {
            result.append(card.toString()).append("\n");
        }
        return result.toString();
    }

    public ArrayList<Card> getCardArrayList() {
        return cardArrayList;
    }
}