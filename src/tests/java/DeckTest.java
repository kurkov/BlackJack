package tests.java;

import model.Deck;
import model.Hand;
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
        Deck deck = server.getDeck();
        int deckSize = deck.getDeckSize();

        for (int i = 0; i < deckSize - 1; i++) {
            deck.deck.remove(0);
        }

        int expected = 1;
        int actual = deck.getDeckSize();
        assertEquals(expected, actual);

        server.getHand();
        expected = 50;
        actual = server.getDeck().getDeckSize();
        assertEquals(expected, actual);
    }
}