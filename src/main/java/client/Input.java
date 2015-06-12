package client;

import java.util.Scanner;

public class Input {

    Output output = new Output();

    public int getBetAmount(int money) {
        Scanner scanner = new Scanner(System.in);
        int bet = scanner.nextInt();
        while (bet > money && bet > 0) {
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
