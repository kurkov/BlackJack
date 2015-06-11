package model;

public class Card {

  private final int suit;
  private final int rank;
  private int cardPoints;

  public final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;

  public final static int TWO = 2, THREE = 3, FOUR = 4, FIVE = 5, SIX = 6,
          SEVEN = 7, EIGHT = 8, NINE = 9, TEN = 10;

  public final static int JACK = 11, QUEEN = 12, KING = 13, ACE = 1;


  public Card(int suit, int rank) {
    assert isValidSuit(suit);
    assert isValidRank(rank);
    this.suit = suit;
    this.rank = rank;
    cardPoints = getCardPoints(rank);
  }

  private int getCardPoints(int rank) {
    switch (rank) {
      case TWO:
        return 2;
      case THREE:
        return 3;
      case FOUR:
        return 4;
      case FIVE:
        return 5;
      case SIX:
        return 6;
      case SEVEN:
        return 7;
      case EIGHT:
        return 8;
      case NINE:
        return 9;
      case TEN:
        return 10;
      case JACK:
        return 10;
      case QUEEN:
        return 10;
      case KING:
        return 10;
      case ACE:
        return 11;
      default:
        return 0;
    }
  }

  private boolean isValidRank(int rank) {
    return ((ACE <= rank) && (rank <= KING));
  }

  private boolean isValidSuit(int suit) {
    return ((SPADES <= suit) && (suit <= CLUBS));
  }

  public String getSuitName() {
    switch (suit) {
      case SPADES:
        return "Spades";
      case HEARTS:
        return "Hearts";
      case DIAMONDS:
        return "Diamonds";
      case CLUBS:
        return "Clubs";
      default:
        return null;
    }
  }

  public String getValueAsString() {
    switch (rank) {
      case ACE:
        return "Ace";
      case TWO:
        return "2";
      case THREE:
        return "3";
      case FOUR:
        return "4";
      case FIVE:
        return "5";
      case SIX:
        return "6";
      case SEVEN:
        return "7";
      case EIGHT:
        return "8";
      case NINE:
        return "9";
      case TEN:
        return "10";
      case JACK:
        return "Jack";
      case QUEEN:
        return "Queen";
      case KING:
        return "King";
      default:
        return null;
    }
  }

  public String toString() {
    return getValueAsString() + " of " + getSuitName();
  }

  public int getSuit() {
    return suit;
  }

  public int getRank() {
    return rank;
  }

  public int getCardPoints() {
    return cardPoints;
  }

  public void setCardPoints(int cardPoints) {
    this.cardPoints = cardPoints;
  }

  public static void main(String[] args) {
    Card card = new Card(DIAMONDS, KING);
    System.out.println(card.toString() + " " + card.getCardPoints());
  }
}
