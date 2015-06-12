package client;

import server.Server;
import server.ServerImpl;

public class GameController {

    private static int userId;
    private static int userMoney;
    private static int userBet;
    private static String userHand;
    private static int userPoints;

    public static void main(String[] args) {

        Server server = new ServerImpl();
        Output output = new Output();
        Input input = new Input();

        String[] connection = getConnection(server, output);

        if ("1".equals(connection[0])) {
            output.startGame();

            userId = Integer.parseInt(connection[1]);

            getUserMoney(server);

            while (userMoney > 0) {

                askBetFromUser(server, input, output);

                sendBetToServer(server);

                String serverResult = "continue";

                while ("continue".equals(serverResult)) {

                    showHandToUser(server, output);

                    String userAction = getVerifiedUserAction(input, output);

                    serverResult = sendActionToServer(server, userAction);

                    showResultToUser(server, output, serverResult);
                }
            }
        }
    }

    private static String[] getConnection(Server server, Output output) {
        String[] connection = server.getConnection();

        if ("1".equals(connection[0])) {
            output.isConnected();
        } else {
            output.isNotConnected();
        }

        return connection;
    }

    private static void getUserMoney(Server server) {
        userMoney = server.getUserMoney(userId);
    }

    private static void askBetFromUser(Server server, Input input, Output output) {
        getUserMoney(server);
        output.showUserMoney(userMoney);
        output.askBetAmount();
        userBet = input.getBetAmount(userMoney);
    }

    private static void sendBetToServer(Server server) {
        server.setUserBet(userId, userBet);
        userHand = server.getHand(userId);
    }

    private static void showHandToUser(Server server, Output output) {
        userPoints = server.getUserPoints(userId);
        userHand = server.getUserCardsToString(userId);
        output.showUserHand(userPoints, userHand);

        int dealerPoints = server.getDealerPoints();
        String dealerHand = server.getDealerCardsToString();
        output.showDealerHand(dealerPoints, dealerHand);
    }

    private static String getUserAction(Input input, Output output) {
        String action;
        output.askUserAction();
        action = input.getUserAction();

        return action;
    }

    private static String getVerifiedUserAction(Input input, Output output) {
        String action = null;
        boolean incorrectAction = false;

        do {
            try {
                action = getUserAction(input, output);

                if ("s".equals(action) || "h".equals(action) || "d".equals(action)) {
                    incorrectAction = false;
                } else {
                    incorrectAction = true;
                    output.incorrectAction();
                }
            } catch (NullPointerException e) {
                output.actionNull();
                System.out.println(e.toString());
                System.exit(0);
            }
        } while (incorrectAction);

        return action;
    }

    private static String sendActionToServer(Server server, String userAction) {
        String serverResult = server.makeUserAction(userId, userAction);
        return serverResult;
    }

    static void showResultToUser(Server server, Output output, String serverResult) {
        getUserMoney(server);
        showHandToUser(server, output);

        if ("win".equals(serverResult)) {
            output.youWin();
        } else if ("bust".equals(serverResult)) {
            //todo
            output.goingBust();
        } else if ("push".equals(serverResult)) {
            //todo
            output.gamePush();
        } else if ("continue".equals(serverResult)) {
            //todo
        }
    }
}