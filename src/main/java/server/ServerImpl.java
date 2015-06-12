package server;

import model.Card;
import model.Deck;
import model.Hand;
import model.User;

import java.util.ArrayList;

public class ServerImpl implements Server {

    private int userBet;

    private User user;

    private Deck deck = new Deck();

    public ServerImpl() {
        deck.shuffle();
    }

    @Override
    public int getConnection() {
        // connected
        return 1;
    }


    @Override
    public int getUserBet() {
        return userBet;
    }

    @Override
    public void setUserBet(int userBet) {
        this.userBet = userBet;
    }

    @Override
    public ArrayList<Card> getHand() {
        Hand hand;
        if (deck.getDeckSize() - 1 == 0) {
            deck = new Deck();
            deck.shuffle();
            hand = new Hand(deck);
        } else {
            hand = new Hand(deck);
        }
        return hand.getHand();
    }

    @Override
    public Card getCardFromDeck() {
        return deck.takeCard();
    }

    @Override
    public Deck getDeck() {
        return deck;
    }
}
