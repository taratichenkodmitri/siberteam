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

    public Integer testCompareHelper(String hand1, String hand2) throws IncorrectHandException, IncorrectSuitException {
        PokerHand pokerHand1 = new PokerHand(hand1);
        PokerHand pokerHand2 = new PokerHand(hand2);

        return pokerHand1.compareTo(pokerHand2);
    }
    @Test
    public void testCompare() throws IncorrectHandException, IncorrectSuitException {
        //Pair and High card
        Assert.assertEquals(new Integer(1), testCompareHelper("2H 2S AD AS TD", "2H QS TD 9S 5D"));

        //Two pairs and Pair
        Assert.assertEquals(new Integer(1), testCompareHelper("2H 3S 2D 6S 6D", "2H 3S 2D AS KD"));

        //Three of a kind and Two pairs
        Assert.assertEquals(new Integer(1), testCompareHelper("AH 3S AD 6S AD", "2H 3S 2D 6S 6D"));

         //Straight and Three of a kind
        Assert.assertEquals(new Integer(1), testCompareHelper("7S 6H 5D 4C 3D", "AH 3S AD 6S AD"));

        //Flush and Straight
        Assert.assertEquals(new Integer(1), testCompareHelper("7D 2D AD TD QD", "7S 6H 5D 4C 3D"));

        //Full house and Flush
        Assert.assertEquals(new Integer(1), testCompareHelper("KS 2H KC 2D KD", "7D 2D AD TD QD"));

        //Four of a kind and Full house
        Assert.assertEquals(new Integer(1), testCompareHelper("KC KS KH 2D KD", "KS 2H KC 2D KD"));

        //Straight flush and Four of a kind
        Assert.assertEquals(new Integer(1), testCompareHelper("2D 3D 4D 5D 6D", "KC KS KH 2D KD"));

        //High card and High card
        Assert.assertEquals(new Integer(1), testCompareHelper("2H QS TD 9S 5D", "3H 8S TD 9S 2D"));

        //Pair and Pair
        Assert.assertEquals(new Integer(1), testCompareHelper("AH 5S TD 9S AD", "AH 8S TD AS 2D"));

        //Two pair and Two pair
        Assert.assertEquals(new Integer(1), testCompareHelper("TH 5S TD 9S 5D", "6H 6S 8D AS 8D"));

        //Two pair and Two pair high card
        Assert.assertEquals(new Integer(1), testCompareHelper("TH 5S TD 9S 5D", "5H 5S TD 7S TD"));

        //Three of a kind and Three of a kind
        Assert.assertEquals(new Integer(1), testCompareHelper("TH 5S TD 9S TD", "9H 9S 9D 6S 7D"));

        //Three of a kind and Three of a kind high card
        Assert.assertEquals(new Integer(1), testCompareHelper("TH 5S TD 9S TD", "TH TS TD 6S 7D"));

        //Straight and Straight
        Assert.assertEquals(new Integer(1), testCompareHelper("2H 3S 4D 5S 6D", "AH 2S 3D 4S 5D"));

        //Flush and Flush
        Assert.assertEquals(new Integer(1), testCompareHelper("2D 5D 8D KD TD", "3D 4D 9D QD TD"));

        //Full house and Full house 3
        Assert.assertEquals(new Integer(1), testCompareHelper("3H 3S 3D 2S 2D", "2H 2S 2D 3S 3D"));

        //Full house and Full house 2
        Assert.assertEquals(new Integer(1), testCompareHelper("3H 3S 3D 5S 5D", "3H 4S 4D 3S 3D"));

        //Four of a kind and Four of a kind
        Assert.assertEquals(new Integer(1), testCompareHelper("3H 3S 3D 3S 5D", "2H 2S 4D 2S 2D"));

        //Four of a kind and Four of a kind high card
        Assert.assertEquals(new Integer(1), testCompareHelper("3H 3S 3D 3S 5D", "3H 3S 4D 3S 3D"));

        //Straight flush and Straight flush
        Assert.assertEquals(new Integer(1), testCompareHelper("2D 3D 4D 5D 6D", "AD 2D 3D 4D 5D"));

        //Straight flush and Straight flush
        Assert.assertEquals(new Integer(1), testCompareHelper("TD JD QD KD AD", "9D TD JD QD KD"));

        //High card and High card equals
        Assert.assertEquals(new Integer(0), testCompareHelper("2H QS TD 9S 5D", "2H QS TD 9S 5D"));

        //Pair and Pair equals
        Assert.assertEquals(new Integer(0), testCompareHelper("AH QS TD 9S AD", "AH QS TD 9S AD"));

        //Two pair and Two pair equals
        Assert.assertEquals(new Integer(0), testCompareHelper("TH 5S TD 9S 5D", "TH 5S TD 9S 5D"));

        //Three of a kind and Three of a kind equals
        Assert.assertEquals(new Integer(0), testCompareHelper("TH 5S TD 9S TD", "TH 5S TD 9S TD"));

        //Straight and Straight equals
        Assert.assertEquals(new Integer(0), testCompareHelper("2H 3S 4D 5S 6D", "2H 3S 4D 5S 6D"));

        //Flush and Flush equals
        Assert.assertEquals(new Integer(0), testCompareHelper("2D 5D 8D KD TD", "2D 5D 8D KD TD"));

    }
}
