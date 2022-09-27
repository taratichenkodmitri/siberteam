package org.example.poker;

import org.example.exception.IncorrectSuitException;

public class Card {
    public Integer value;
    public Suit suit;
    public Character denomination;

    public Card(Character denomination, Character suit) throws IncorrectSuitException {
        initValue(denomination);
        initSuit(suit);
    }

    private void initValue(Character denomination) {
        this.value = ValueUtil.getValue(denomination);
    }

    private void initSuit(Character suit) throws IncorrectSuitException {
        switch (suit) {
            case 'S':
                this.suit = Suit.SPADES;
                break;
            case 'H':
                this.suit = Suit.HEARTS;
                break;
            case 'D':
                this.suit = Suit.DIAMONDS;
                break;
            case  'C':
                this.suit = Suit.CLUBS;
                break;
            default:
                throw new IncorrectSuitException();
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Character getDenomination() {
        return denomination;
    }

    public void setDenomination(Character denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suit=" + suit +
                ", denomination=" + denomination +
                '}';
    }
}
