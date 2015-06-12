package model;

import java.util.ArrayList;

public class Hand extends Deck {

    private ArrayList<Card> hand;

    public Hand(Deck deck) {
        hand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            hand.add(deck.takeCard());
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
