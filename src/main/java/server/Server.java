package server;

import model.Card;
import model.Deck;

import java.util.ArrayList;

public interface Server {

    public int getConnection();

    public int getUserBet();

    public void setUserBet(int userBet);

    public ArrayList<Card> getHand();

    public Card getCardFromDeck();

    Deck getDeck();
}
