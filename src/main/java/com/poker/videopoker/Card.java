package com.poker.videopoker;

public class Card {
    private String rank;
    private String suit;

    public Card(){}
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    public void setSuit(String suit){
        this.suit = suit;
    }
    public void setRank(String rank){
        this.rank =rank;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return rank.equals(card.rank) && suit.equals(card.suit);
    }

    @Override
    public int hashCode() {
        return rank.hashCode() + suit.hashCode();
    }

    public String toString() {
        return rank + suit;
    }
}