package server;

import model.Card;
import model.Deck;

public interface Server {

    String[] getConnection();

    int getUserBet(int userId);

    void setUserBet(int userId, int userBet);

    int getUserMoney(int userId);

    void setUserMoney(int userId, int money);

    String getHand(int userId);

    Card getCardFromDeck();

    Deck getDeck();

    String makeUserAction(int userId, String action);

    String getUserCardsToString(int userId);

    int getUserPoints(int userId);

    int getDealerPoints();

    String getDealerCardsToString();
}
