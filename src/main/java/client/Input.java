package client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    Output output = new Output();

    public int getBetAmount(int money) {
        Scanner scanner = new Scanner(System.in);
        int bet = -1;

        while (bet > money || bet <= 0) {

            try {
                bet = scanner.nextInt();
            } catch (InputMismatchException e) {
                output.numbersOnly();
                scanner.next();
            }

            if (bet == 0) {
                output.cantMakeZeroBet();
            } else if (bet > money) {
                output.cantMakeBet();
            }
        }

        return bet;
    }
    
    public String getUserAction() {
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        return action;
    }
}
