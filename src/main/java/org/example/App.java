package org.example;

import org.example.poker.PokerEvaluation;
import org.example.poker.PokerHand;

public class App {
    public static void main( String[] args ) {
        try {
            PokerHand pokerHand1 = new PokerHand("2D 2H 3C 3C KD");
            PokerEvaluation pokerEvaluation1 = new PokerEvaluation(pokerHand1);

            PokerHand pokerHand = new PokerHand("AD AH 2C QC 2D");
            PokerEvaluation pokerEvaluation = new PokerEvaluation(pokerHand);

            System.out.println(pokerEvaluation1.getValueHand());
            System.out.println(pokerEvaluation.getValueHand());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
