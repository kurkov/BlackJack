package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    public List<Card> cardList;

    public Deck() {

        cardList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cardList.add(new Card(i, j));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public int getDeckSize() {
        return cardList.size();
    }

    public Card takeCard() {
        if (cardList.size() == 0) {
            Deck deck = new Deck();
            deck.shuffle();
            cardList = deck.getCardList();
        }
        Card card = cardList.get(0);
        cardList.remove(0);
        return card;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : cardList) {
            result.append(card.toString()).append("\n");
        }
        return result.toString();
    }

    public List<Card> getCardList() {
        return cardList;
    }
}