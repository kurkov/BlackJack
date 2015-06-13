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

    public void showUserMoney(int money) {
        System.out.println("You have: $" + money);
    }

    public void showUserMoneyAfterBet(int money) {
        System.out.println("You made bet. Money left: $" + money + "\n");
    }

    public void askUserAction() {
        System.out.println("What would you like to do? (s = STAND, h = HIT, d = DOUBLE)");
    }

    public void showUserHand(int userPoints, String userCards) {
        System.out.println("Your hand is (" + userPoints + " points):");
        System.out.println(userCards);
    }

    public void showDealerHand(int dealerPoints, String dealerHand) {
        System.out.println("Dealer's hand is (" + dealerPoints + " points):");
        System.out.println(dealerHand);
    }

    public void incorrectAction() {
        System.out.println("Incorrect action!");
    }

    public void actionNull() {
        System.out.println("Game stopped. Program Error: action = null");
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
}
