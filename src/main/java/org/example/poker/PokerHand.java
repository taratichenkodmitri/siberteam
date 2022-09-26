package org.example.poker;

import org.example.exception.IncorrectHandException;

import java.util.regex.Pattern;

public class PokerHand {
    private String hand;
    private String patternHand = "[2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC]";
    public PokerHand(String hand) throws IncorrectHandException {
        if(!Pattern.matches(this.patternHand, hand)) {
            throw new IncorrectHandException();
        }

        this.hand = hand;
    }

    public String getHand() {
        return hand;
    }
}
