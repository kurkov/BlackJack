package client;

import model.Card;
import model.User;
import server.Server;
import server.ServerImpl;

public class GameController {

    private static final int INITIAL_CASH = 100;

    public static void main(String[] args) {

        Server server = new ServerImpl();
        Output output = new Output();
        Input input = new Input();

        int connection = server.getConnection();
        if (connection == 1) {
            output.isConnected();
        } else {
            output.isNotConnected();
        }

        if (connection == 1) {

            User user = new User(INITIAL_CASH);

            output.startGame();

            while (user.getMoney() > 0) {

                output.showUserMoney(user);
                output.askBetAmount();
                int betAmount = input.getBetAmount(user);
                user.setBet(betAmount);

                user.setMoney(user.getMoney() - user.getBet());
                output.showUserMoneyAfterBet(user);

                sendBetToServer(server, user.getBet());

                user.getNewHand(server.getHand());
                output.showUserHand(user);
                user.showHand();


                while (user.getHandPoints() <= 21) {
                    String action = getVerifiedUserAction(output, input);
                    if (action.equals("h")) {
                        doActionHit(server, output, user);
                        sendUserStateToServer(server, user);
                    } else if (action.equals("s")) {
                        //todo stand
                    } else if (action.equals("d")) {
                        //todo double
                    }
                }

            }
        }
    }

    private static void doActionHit(Server server, Output output, User user) {
        Card card = server.getCardFromDeck();
        user.addCardToHand(card);
        output.showUserHand(user);
        user.showHand();
    }

    private static String getVerifiedUserAction(Output output, Input input) {
        String action;
        boolean incorrectAction;
        do {
            action = getUserAction(output, input);
            if (action.equals("s") || action.equals("h") || action.equals("d")) {
                incorrectAction = false;
            } else {
                incorrectAction = true;
                output.incorrectAction();
            }
        } while (incorrectAction);
        return action;
    }

    private static String getUserAction(Output output, Input input) {
        String action;
        output.askUserAction();
        action = input.getUserAction();
        return action;
    }

    private static void sendBetToServer(Server server, int bet) {
        server.setUserBet(bet);
    }

    private static void sendUserStateToServer(Server server, User user) {

    }
}
