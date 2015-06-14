package server;

import model.Card;
import model.Deck;
import model.Hand;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements Server {

    private static final int MAX_PLAYERS = 5;
    private static final int INITIAL_CASH = 100;

    private List<User> userList = new ArrayList<>();

    private static User dealer;

    private static Deck deck = new Deck();

    private int previousUserPoints;
    private int previousDealerPoints;
    private int standCounter;

    public ServerImpl() {
        deck.shuffle();
        dealer = new User();
    }

    @Override
    public String[] getConnection() {
        String[] connectionResult = new String[2];

        if (userList.size() < MAX_PLAYERS) {
            User user = new User(INITIAL_CASH);
            userList.add(user);
            connectionResult[0] = "1"; // connection successful
            int userId = userList.indexOf(user);
            connectionResult[1] = String.valueOf(userId);
        } else {
            connectionResult[0] = "-1"; // not connected
        }

        return connectionResult;
    }

    @Override
    public int getUserBet(int userId) {
        return userList.get(userId).getBet();
    }

    @Override
    public void setUserBet(int userId, int userBet) {
        User user = userList.get(userId);
        user.setBet(userBet);
        user.setMoney(user.getMoney() - userBet);
    }

    @Override
    public int getUserMoney(int userId) {
        return userList.get(userId).getMoney();
    }

    @Override
    public void setUserMoney(int userId, int money) {
        userList.get(userId).setMoney(money);
    }

    @Override
    public String getHand(int userId) {
        Hand hand;

        if (deck.getDeckSize() - 1 == 0) {
            deck = new Deck();
            deck.shuffle();
            hand = new Hand(deck);
        } else {
            hand = new Hand(deck);
        }

        userList.get(userId).setHand(hand.getCards());

        dealerGetHand();

        return userList.get(userId).showHand();
    }

    public static void dealerGetHand() {
        Hand hand;

        if (deck.getDeckSize() - 1 == 0) {
            deck = new Deck();
            deck.shuffle();
            hand = new Hand(deck);
        } else {
            hand = new Hand(deck);
        }

        dealer.setHand(hand.getCards());
    }

    @Override
    public String getUserCardsToString(int userId) {
        return userList.get(userId).showHand();
    }

    @Override
    public String getDealerCardsToString() {
        return dealer.showHand();
    }

    @Override
    public int getUserPoints(int userId) {
        return userList.get(userId).getHandPoints();
    }

    @Override
    public int getDealerPoints() {
        return dealer.getHandPoints();
    }

    @Override
    public Card getCardFromDeck() {
        return deck.takeCard();
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public String makeUserAction(int userId, String action) {
        String serverResult = "-1";
        if ("h".equals(action)) {
            doActionHit(userId);
            standCounter = 0;
        } else if ("s".equals(action)) {
            // just stand
        } else if ("d".equals(action)) {
            doActionDouble(userId);
            standCounter = 0;
        }

        makeDealerAction();
        serverResult = checkGameResults(userId);

        return serverResult;
    }

    private void doActionHit(int userId) {
        User user = userList.get(userId);
        user.addCardToHand(getCardFromDeck());
    }

    private void doActionDouble(int userId) {
        User user = userList.get(userId);
        user.setMoney(user.getMoney() - user.getBet());
        user.setBet(user.getBet() * 2);
        user.addCardToHand(getCardFromDeck());
    }

    private void makeDealerAction() {
        int dealerPoints = getDealerPoints();
        if (dealerPoints < 17) {
            dealer.addCardToHand(getCardFromDeck());
            standCounter = 0;
        }
    }

    private String checkGameResults(int userId) {
        String serverResult = "-1";
        int dealerPoints = dealer.getHandPoints();
        int userPoints = userList.get(userId).getHandPoints();

        if (dealerPoints == previousDealerPoints && userPoints == previousUserPoints) {
            standCounter++;
        }

        if ((dealerPoints == 21 && userPoints == 21) || (userPoints > 21 && dealerPoints > 21)) {
            serverResult = "push";
            doPushServerAction(userId);

        } else if (dealerPoints > 21 || userPoints > 21) {

            if (userPoints <= 21 && dealerPoints > 21) {
                serverResult = "win";
                doWinServerAction(userId);

            } else if (dealerPoints <= 21 && userPoints > 21) {
                serverResult = "bust";
            }

        } else if (userPoints < 21 && dealerPoints < 21) {
            serverResult = "continue";
        }

        previousDealerPoints = dealer.getHandPoints();
        previousUserPoints = userList.get(userId).getHandPoints();

        if (standCounter == 1) {

            if (userPoints <= 21 && userPoints > dealerPoints) {
                serverResult = "techwin";
                doWinServerAction(userId);

            } else if (dealerPoints <= 21 && dealerPoints > userPoints) {
                serverResult = "techlose";

            } else if (userPoints == dealerPoints) {
                serverResult = "push";
                doPushServerAction(userId);
            }
        }

        return serverResult;
    }

    private void doWinServerAction(int userId) {
        // send the won money to user
        User user = userList.get(userId);
        int userBet = user.getBet();
        int userMoney = user.getMoney();
        int money = userMoney + (userBet * 2);
        userList.get(userId).setMoney(money);
    }

    private void doPushServerAction(int userId) {
        // send money back to user
        int userBet = userList.get(userId).getBet();
        int userMoney = userList.get(userId).getMoney();
        userList.get(userId).setMoney(userMoney + userBet);
    }
}
