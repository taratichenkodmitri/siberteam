package org.example;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;
import org.example.poker.PokerEvaluation;
import org.example.poker.PokerHand;

public class App {
    public static void main( String[] args ) {
        try {
            PokerHand pokerHand = new PokerHand("2D 2H 2C 2C KD");
            PokerEvaluation pokerEvaluation = new PokerEvaluation(pokerHand);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
