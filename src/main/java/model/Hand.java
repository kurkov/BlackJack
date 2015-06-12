package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand;

    public Hand(Deck deck) {
        hand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            hand.add(deck.takeCard());
        }
    }

    public List<Card> getCards() {
        return hand;
    }
}
