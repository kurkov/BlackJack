package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;

    private int money;
    private int bet;
    private List<Card> hand;

    public User(int money) {
        this.money = money;
        hand = new ArrayList<>();
    }

    // this constructor is for Dealer
    public User() {
        hand = new ArrayList<>();
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

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public String showHand() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            result.append(hand.get(i).toString()).append("\n");
        }

        return result.toString();
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int getHandPoints() {
        int points = 0;
        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i).getCardPoints();
        }
        return points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
