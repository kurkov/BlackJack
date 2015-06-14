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

                sendBetToServer(server, output);

                String serverResult = "continue";

                showHandToUser(server, output);

                while ("continue".equals(serverResult)) {

                    String userAction = getVerifiedUserAction(server, input, output);

                    serverResult = sendActionToServer(server, userAction);

                    showResultToUser(server, output, serverResult);
                }
            }

            output.gameOver();
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

    private static void sendBetToServer(Server server, Output output) {
        server.setUserBet(userId, userBet);
        userHand = server.getHand(userId);
        getUserMoney(server);
        output.showUserMoneyAfterBet(userMoney);
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

    private static String getVerifiedUserAction(Server server, Input input, Output output) {
        String action = null;
        boolean incorrectAction = false;

        do {
            try {
                action = getUserAction(input, output);

                if ("s".equals(action) || "h".equals(action) || "d".equals(action)) {

                    if ("s".equals(action) || "h".equals(action)) {
                        incorrectAction = false;

                    } else if ("d".equals(action)) {
                        incorrectAction = checkBeforeDouble(server, output);
                    }

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

    private static boolean checkBeforeDouble(Server server, Output output) {
        boolean incorrectAction = true;
        getUserMoney(server);

        if (userBet > userMoney) {
            output.cantMakeDouble();
        } else if (userBet <= userMoney) {
            //make double
            userMoney = userMoney - userBet;
            userBet = userBet * 2;
            output.madeDouble(userBet, userMoney);
            incorrectAction = false;
        }

        return incorrectAction;
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
            output.goingBust();
        } else if ("push".equals(serverResult)) {
            output.gamePush();
        } else if ("techwin".equals(serverResult)) {
            output.techWin();
        } else if ("techlose".equals(serverResult)) {
            output.techLose();
        }
    }
}