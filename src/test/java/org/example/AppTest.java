package org.example;

import org.example.exception.IncorrectHandException;
import org.example.poker.PokerHand;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AppTest {

    @Test(expected = IncorrectHandException.class)
    public void testCreatingInvalidHand() throws IncorrectHandException {
        PokerHand pokerHand = new PokerHand("2Q");
    }

    @Test
    public void testCreatingValidHand() throws IncorrectHandException {
        final String HAND = "KS 2H 5C JD TD";

        PokerHand pokerHand = new PokerHand(HAND);
        Assert.assertEquals(HAND, pokerHand.getHand());
    }
}
