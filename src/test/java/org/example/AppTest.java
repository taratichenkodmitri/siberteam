package org.example;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;
import org.example.poker.PokerEvaluation;
import org.example.poker.PokerHand;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Test
    public void testCountDuplicates() throws IncorrectHandException, IncorrectSuitException {
        PokerHand pokerHand1 = new PokerHand("2D 2H 2C 2C KD");
        PokerEvaluation pokerEvaluation1 = new PokerEvaluation(pokerHand1);
        Map<Integer, Integer> actual1 = new HashMap<>();
        Map<Integer, Integer> expected1 = pokerEvaluation1.getDuplicates();
        actual1.put(4,1);
        actual1.put(1, 1);
        Assert.assertEquals(true, expected1.equals(actual1));

        PokerHand pokerHand2 = new PokerHand("2D 2H 2C KC KD");
        PokerEvaluation pokerEvaluation2 = new PokerEvaluation(pokerHand2);
        Map<Integer, Integer> actual2 = new HashMap<>();
        Map<Integer, Integer> expected2 = pokerEvaluation2.getDuplicates();
        actual2.put(3,1);
        actual2.put(2, 1);
        Assert.assertEquals(true, expected2.equals(actual2));

        PokerHand pokerHand3 = new PokerHand("2D 3H 4C 5C 6D");
        PokerEvaluation pokerEvaluation3 = new PokerEvaluation(pokerHand3);
        Map<Integer, Integer> actual3 = new HashMap<>();
        Map<Integer, Integer> expected3 = pokerEvaluation3.getDuplicates();
        actual3.put(1,5);
        Assert.assertEquals(true, expected3.equals(actual3));

        PokerHand pokerHand4 = new PokerHand("2D 2H 4C 4C 6D");
        PokerEvaluation pokerEvaluation4 = new PokerEvaluation(pokerHand4);
        Map<Integer, Integer> actual4 = new HashMap<>();
        Map<Integer, Integer> expected4 = pokerEvaluation4.getDuplicates();
        actual4.put(2,2);
        actual4.put(1,1);
        Assert.assertEquals(true, expected4.equals(actual4));
    }

    public Integer testEvaluateHelper(String hand) throws IncorrectHandException, IncorrectSuitException {
        PokerHand pokerHand = new PokerHand(hand);
        PokerEvaluation pokerEvaluation = new PokerEvaluation(pokerHand);
        return pokerEvaluation.evaluate();
    }
    @Test
    public void testEvaluate() throws IncorrectHandException, IncorrectSuitException {
        Assert.assertEquals(new Integer(9), testEvaluateHelper("AD 2D 3D 4D 5D"));

        Assert.assertEquals(new Integer(8), testEvaluateHelper("2H 2S 3D 2H 2D"));

        Assert.assertEquals(new Integer(7), testEvaluateHelper("3H 2S 3D 2H 2D"));

        Assert.assertEquals(new Integer(6), testEvaluateHelper("AD 9D QD 2D TD"));

        Assert.assertEquals(new Integer(5), testEvaluateHelper("AH 2S 3D 4S 5D"));

        Assert.assertEquals(new Integer(5), testEvaluateHelper("6H 2S 3D 4S 5D"));

        Assert.assertEquals(new Integer(5), testEvaluateHelper("6H 2S 3D 4S 5D"));

        Assert.assertEquals(new Integer(4), testEvaluateHelper("6H 2S 3D 6S 6D"));

        Assert.assertEquals(new Integer(3), testEvaluateHelper("2H 3S 2D 6S 6D"));

        Assert.assertEquals(new Integer(2), testEvaluateHelper("2H 3S 2D AS KD"));

        Assert.assertEquals(new Integer(1), testEvaluateHelper("2H QS TD 9S 5D"));
    }
}
