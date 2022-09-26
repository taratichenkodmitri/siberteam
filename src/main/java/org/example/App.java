package org.example;

import org.example.exception.IncorrectHandException;
import org.example.poker.PokerHand;

public class App {
    public static void main( String[] args ) {
        try {
            PokerHand pokerHand = new PokerHand("2S");
        } catch (IncorrectHandException e) {
            System.out.println(e.getMessage());
        }
    }
}
