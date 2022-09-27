package org.example.poker;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PokerEvaluation {
    private PokerHand pokerHand;
    List<Suit> suits;
    List<Card> cards;

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
}
