package org.example.poker;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;

import java.util.*;
import java.util.regex.Pattern;

public class PokerHand {
    private final String patternHand = "[2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC]";
    private String hand;

    private List<Card> cards = new ArrayList<>();

    public PokerHand(String hand) throws IncorrectHandException, IncorrectSuitException {
        if(!Pattern.matches(this.patternHand, hand)) {
            throw new IncorrectHandException();
        }
        this.hand = hand;
        initCards();
    }

    private void initCards() throws IncorrectSuitException {
        String[] cardsString = this.hand.split(" ");
        for(String card: cardsString) {
           this.cards.add(new Card(card.charAt(0), card.charAt(1)));
        }
        Collections.sort(this.cards, Comparator.comparing(Card::getValue));
    }
    public String getHand() {
        return hand;
    }
}
