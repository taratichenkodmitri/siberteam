package org.example;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;
import org.example.poker.PokerHand;

public class App {
    public static void main( String[] args ) {
        try {
            PokerHand pokerHand = new PokerHand("KD 2H 5C JC TD");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
