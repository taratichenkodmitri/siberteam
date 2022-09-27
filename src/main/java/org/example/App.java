package org.example;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;
import org.example.poker.PokerEvaluation;
import org.example.poker.PokerHand;

public class App {
    public static void main( String[] args ) {
        try {
            PokerHand pokerHand = new PokerHand("KD 2H 2C KC KD");
            PokerEvaluation pokerEvaluation = new PokerEvaluation(pokerHand);
            pokerEvaluation.countCardsInHand();
            System.out.println(pokerEvaluation.getCountsCardsInHand());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
