package org.example;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;
import org.example.poker.PokerEvaluation;
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

    @Test
    public void testStraight() throws IncorrectHandException, IncorrectSuitException {
        final String HAND1 = "2S 3H 4C 5D 6D";
        PokerHand pokerHand1 = new PokerHand(HAND1);
        PokerEvaluation pokerEvaluation = new PokerEvaluation(pokerHand1);
        Assert.assertEquals(true, pokerEvaluation.isStraight());

        final String HAND2 = "2S 3H 9C 5D 6D";
        PokerHand pokerHand2 = new PokerHand(HAND2);
        pokerEvaluation = new PokerEvaluation(pokerHand2);
        Assert.assertEquals(false, pokerEvaluation.isStraight());

        final String HAND3 = "TS JH QC KD AD";
        PokerHand pokerHand3 = new PokerHand(HAND3);
        pokerEvaluation = new PokerEvaluation(pokerHand3);
        Assert.assertEquals(true, pokerEvaluation.isStraight());

        final String HAND4 = "AS 2H 3C 4D 5D";
        PokerHand pokerHand4 = new PokerHand(HAND4);
        pokerEvaluation = new PokerEvaluation(pokerHand4);
        Assert.assertEquals(true, pokerEvaluation.isStraight());

    }

    @Test
    public void testFlush() throws IncorrectHandException, IncorrectSuitException {
        final String HAND1 = "AD QD 8D 3D 2D";
        PokerHand pokerHand1 = new PokerHand(HAND1);
        PokerEvaluation pokerEvaluation = new PokerEvaluation(pokerHand1);
        Assert.assertEquals(true, pokerEvaluation.isFlush());

        final String HAND2 = "AD QH 8H 3H 2D";
        PokerHand pokerHand2 = new PokerHand(HAND2);
        pokerEvaluation = new PokerEvaluation(pokerHand2);
        Assert.assertEquals(false, pokerEvaluation.isFlush());
    }
}
