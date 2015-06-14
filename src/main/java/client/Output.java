package client;

public class Output {

    public void isConnected() {
        System.out.println("Connected to server");
    }

    public void isNotConnected() {
        System.out.println("Connection problems");
    }

    public void startGame() {
        System.out.println("Started new game");
    }

    public void askBetAmount() {
        System.out.println("What is your bet amount?");
    }

    public void cantMakeBet() {
        System.out.println("You don't have enough money to make such bet. Please make smaller bet");
    }

    public void cantMakeZeroBet() {
        System.out.println("Zero bet is not allowed");
    }

    public void showUserMoney(int money) {
        System.out.format("You have: $%d%n", money);
    }

    public void showUserMoneyAfterBet(int money) {
        System.out.format("You made bet. Money left: $%d%n%n", money);
    }

    public void askUserAction() {
        System.out.println("What would you like to do? (s = STAND, h = HIT, d = DOUBLE)");
    }

    public void showUserHand(int userPoints, String userCards) {
        System.out.format("Your hand is (%d points):%n", userPoints);
        System.out.println(userCards);
    }

    public void showDealerHand(int dealerPoints, String dealerHand) {
        System.out.format("Dealer's hand is (%d points):%n", dealerPoints);
        System.out.println(dealerHand);
    }

    public void incorrectAction() {
        System.out.println("Incorrect action!\n");
    }

    public void numbersOnly() {
        System.out.println("Write numbers only!");
    }

    public void actionNull() {
        System.out.println("Game stopped. Program Error: action = null\n");
    }

    public void youWin() {
        System.out.println("Congratulations. You win!\n");
    }

    public void goingBust() {
        System.out.println("You busted!\n");
    }

    public void gamePush() {
        System.out.println("Push...\n");
    }

    public void cantMakeDouble() {
        System.out.println("You don't have enough money to Double!\n");
    }

    public void madeDouble(int userBet, int userMoney) {
        System.out.format("You made Double. Your bet is: $%d. Money left: $%d%n%n", userBet, userMoney);
    }

    public void techWin() {
        System.out.println("2 time stand. Congratulations. You win by points!\n");
    }

    public void techLose() {
        System.out.println("2 time stand. You lose by points!\n");
    }

    public void gameOver() {
        System.out.println("You don't have money to play. Game over :)");
    }
}
