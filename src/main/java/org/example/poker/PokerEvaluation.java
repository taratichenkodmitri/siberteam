package org.example.poker;

import java.util.*;

public class PokerEvaluation {
    private PokerHand pokerHand;
    private List<Suit> suits;
    private List<Card> cards;
    private Map<Character, Integer> countsCardsInHand = new HashMap<>();
    private Map<Integer, Integer> duplicates = new HashMap<>();
    private Integer rank;
    public PokerEvaluation(PokerHand pokerHand) {
        this.pokerHand = pokerHand;
        this.suits = pokerHand.getSuits();
        this.cards = pokerHand.getCards();
        this.countCardsInHand();
        this.countDuplicates();
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

    private void countCardsInHand() {
        for(Card card: this.cards) {
            Character denomination = card.getDenomination();
            if(!this.countsCardsInHand.containsKey(denomination)) {
                this.countsCardsInHand.put(denomination, 1);
            } else {
                this.countsCardsInHand.put(denomination, this.countsCardsInHand.get(denomination) + 1);
            }
        }
    }

    private void countDuplicates() {
        for(Map.Entry<Character, Integer> entry: this.countsCardsInHand.entrySet()) {
            Integer count = entry.getValue();
            if(!this.duplicates.containsKey(count)) {
                this.duplicates.put(count, 1);
            } else {
                this.duplicates.put(count, this.duplicates.get(count) + 1);
            }
        }
    }

    public Integer evaluate() {
        if (this.isFlush() && this.isStraight()) {
            this.rank = 9;
            return this.rank;
        }
        if (this.duplicates.containsKey(4)) {
            this.rank = 8;
            return this.rank;
        }
        if (this.duplicates.containsKey(3) && this.duplicates.containsKey(2)) {
            this.rank = 7;
            return this.rank;
        }
        if(isFlush()) {
            this.rank = 6;
            return this.rank;
        }
        if(isStraight()) {
            this.rank = 5;
            return this.rank;
        }
        if(this.duplicates.containsKey(3)) {
            this.rank = 4;
            return this.rank;
        }
        if(this.duplicates.containsKey(2)) {
            if(this.duplicates.get(2) > 1) {
                this.rank = 3;
                return this.rank;
            }
            this.rank = 2;
            return this.rank;
        }
        return 1;
    }
    public Map<Integer, Integer> getDuplicates() {
        return duplicates;
    }
}
