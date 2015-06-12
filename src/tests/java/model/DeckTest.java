package model;

import org.junit.Test;
import server.Server;
import server.ServerImpl;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    @Test
    public void testDeck() {
        Deck deck = new Deck();
        deck.shuffle();
        int expected = 52;
        int actual = deck.getDeckSize();
        assertEquals(expected, actual);
        //System.out.println(deck.toString());
    }

    @Test
    public void testGetHandFromFullDeck() {
        Deck deck = new Deck();
        //System.out.println(deck.toString());
        Hand hand = new Hand(deck);
        int expected = 50;
        int actual = deck.getDeckSize();
        assertEquals(expected, actual);
        //System.out.println(deck.toString());
    }

    @Test
    public void testGetHandFromSmallDeck() {
        Server server = new ServerImpl();
        String[] connection = server.getConnection();
        int userId = Integer.parseInt(connection[1]);
        Deck deck = server.getDeck();
        int deckSize = deck.getDeckSize();

        for (int i = 0; i < deckSize - 1; i++) {
            deck.cardList.remove(0);
        }

        int expected = 1;
        int actual = deck.getDeckSize();
        assertEquals(expected, actual);

        server.getHand(userId);
        expected = 48;
        actual = server.getDeck().getDeckSize();
        assertEquals(expected, actual);
    }
}