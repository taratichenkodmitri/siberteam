package org.example.poker;

import org.example.exception.IncorrectHandException;
import org.example.exception.IncorrectSuitException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PokerHand {
    private final String patternHand = "[2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC] [2-9TJQKA][SHDC]";
    private String hand;

    private List<Card> cards = new ArrayList<>();
    private List<Suit> suits = new ArrayList<>();

    public PokerHand(String hand) throws IncorrectHandException, IncorrectSuitException {
        if(!Pattern.matches(this.patternHand, hand)) {
            throw new IncorrectHandException();
        }
        this.hand = hand;
        this.initCards();
        this.initSuits();
    }

    private void initCards() throws IncorrectSuitException {
        String[] cardsString = this.hand.split(" ");
        for(String card: cardsString) {
           this.cards.add(new Card(card.charAt(0), card.charAt(1)));
        }
        Collections.sort(this.cards, Comparator.comparing(Card::getValue));
    }

    private void initSuits() {
        this.suits = this.cards.stream().map(item -> item.getSuit()).collect(Collectors.toList());
        Collections.sort(this.suits);
    }
    public String getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Suit> getSuits() {
        return suits;
    }
}
