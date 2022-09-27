package org.example.poker;

import java.util.*;

public class PokerEvaluation {
    private PokerHand pokerHand;
    private List<Suit> suits;
    private List<Card> cards;
    private Map<Character, Integer> countsCardsInHand = new HashMap<>();

    public PokerEvaluation(PokerHand pokerHand) {
        this.pokerHand = pokerHand;
        this.suits = pokerHand.getSuits();
        this.cards = pokerHand.getCards();
    }

    public Boolean isFlush() {
        return this.suits.get(0) == this.suits.get(4);
    }

    public Boolean isStraight() {
        Card previousCard = this.cards.get(0);
        Card currentCard;
        if (this.cards.get(4).getDenomination() == 'A') {
            if(isMinStraight()) {
                return true;
            } else {
                Card ace = this.cards.get(0);
                ace.setValue(14);
                Collections.sort(pokerHand.getCards(), Comparator.comparing(Card::getValue));
                this.cards = pokerHand.getCards();
            }
        }
        for (int i = 1; i < this.cards.size(); i++) {
            currentCard = this.cards.get(i);
            if(currentCard.getValue() - previousCard.getValue() != 1) {
                return false;
            }
            previousCard = currentCard;
        }
        return true;
    }
    private Boolean isMinStraight() {
        Card ace = this.cards.get(4);
        ace.setValue(1);
        Collections.sort(pokerHand.getCards(), Comparator.comparing(Card::getValue));
        this.cards = pokerHand.getCards();
        return isStraight();
    }

    public void countCardsInHand() {
        for(Card card: this.cards) {
            System.out.println("hi");
            Character denomination = card.getDenomination();
            if(!this.countsCardsInHand.containsKey(denomination)) {
                this.countsCardsInHand.put(denomination, 1);
            } else {
                this.countsCardsInHand.put(denomination, this.countsCardsInHand.get(denomination) + 1);
            }
        }
    }

    public Map<Character, Integer> getCountsCardsInHand() {
        return this.countsCardsInHand;
    }
}
