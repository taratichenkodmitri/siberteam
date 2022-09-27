package org.example;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;
import org.example.poker.PokerHand;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test(expected = IncorrectHandException.class)
    public void testCreatingInvalidHand() throws IncorrectHandException, IncorrectSuitException {
        PokerHand pokerHand = new PokerHand("2Q");
    }
    @Test(expected = IncorrectHandException.class)
    public void testCreatingInvalidHandExtraCharacters() throws IncorrectHandException, IncorrectSuitException {
        PokerHand pokerHand = new PokerHand("KS KS 2H 5C JD TD KS");
    }
    @Test
    public void testCreatingValidHand() throws IncorrectHandException, IncorrectSuitException {
        final String HAND = "KS 2H 5C JD TD";

        PokerHand pokerHand = new PokerHand(HAND);
        Assert.assertEquals(HAND, pokerHand.getHand());
    }
}
