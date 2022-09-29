package org.example;

import org.example.poker.PokerHand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        try {
            List<PokerHand> pokerHandList = new ArrayList<>();
            pokerHandList.add(new PokerHand("2D 2H 3C 3C KD"));
            pokerHandList.add(new PokerHand("2D 5H 6C TC AD"));
            pokerHandList.add(new PokerHand("AD 2H 3C 4C 5D"));
            pokerHandList.add(new PokerHand("2D 3H 4C 5C 6D"));
            pokerHandList.add(new PokerHand("5D 6H 7C 8C 9D"));
            pokerHandList.add(new PokerHand("9D TH JC QC KD"));
            pokerHandList.add(new PokerHand("TD JH QC KC AD"));
            pokerHandList.add(new PokerHand("2D 3D 4D 5D 6D"));
            pokerHandList.add(new PokerHand("AD 2D 3D 4D 5D"));
            pokerHandList.add(new PokerHand("AD 3D 4D 5D 6D"));
            pokerHandList.add(new PokerHand("TD JD QD KD AD"));
            pokerHandList.add(new PokerHand("TD 3H 2C 7C 9S"));
            pokerHandList.add(new PokerHand("2D 3H 5C 8C 9S"));
            pokerHandList.add(new PokerHand("AD AH AC AC QS"));
            pokerHandList.add(new PokerHand("AD AH AC AC KS"));
            pokerHandList.add(new PokerHand("2D 2H 2C 2C 3S"));
            pokerHandList.add(new PokerHand("2D 2H 2C 2C 4S"));
            pokerHandList.add(new PokerHand("9S TS JS QS KS"));
            pokerHandList.add(new PokerHand("2D 2H 5C 5C KD"));
            pokerHandList.add(new PokerHand("2D 2H 5C 5C AD"));

            pokerHandList.forEach(item -> {
                System.out.println(item.getHand());
            });

            System.out.println("***************************************************************");

            Collections.sort(pokerHandList);

            pokerHandList.forEach(item -> {
                System.out.println(item.getHand());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
