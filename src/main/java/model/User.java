package model;

import java.util.ArrayList;

public class User {

    private int money;
    private int bet;

    private ArrayList<Card> hand;

    public User(int money) {
        this.money = money;
        hand = new ArrayList<Card>();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void getNewHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void showHand(){
        String result = "";
        for (int i = 0; i < hand.size(); i++) {
            result += hand.get(i).toString() + "\n";
        }
        System.out.println(result);
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int getHandPoints(){
        int points = 0;
        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i).getCardPoints();
        }
        return points;
    }
}
