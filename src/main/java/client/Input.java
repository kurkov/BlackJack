package client;

import model.User;

import java.util.Scanner;

public class Input {

    Output output = new Output();

    public int getBetAmount(User user) {
        int money = user.getMoney();
        Scanner scanner = new Scanner(System.in);
        int bet = scanner.nextInt();
        while (bet > money) {
            output.cantMakeBet();
            output.askBetAmount();
            bet = scanner.nextInt();
        }
        return bet;
    }
    
    public String getUserAction() {
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        return action;
    }
}
