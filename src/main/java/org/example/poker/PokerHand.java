package org.example.poker;

import org.example.exception.IncorrectHandException;

import java.util.regex.Pattern;

public class PokerHand {
    private String hand;
    private String pattern = "[2-9TJQKA][SHDC]";
    public PokerHand(String hand) throws IncorrectHandException {
        if(!Pattern.matches("[2-9TJQKA][SHDC]",hand)) {
            throw new IncorrectHandException();
        }

        this.hand = hand;
    }

    public String getHand() {
        return hand;
    }
}
