package com.poker.videopoker;

import java.util.List;

public class DrawRequest {
    private List<Card> cards;
    private List<Boolean> held;
    private int bet;

    public DrawRequest(List<Card> cards, List<Boolean> held, int bet){
        this.cards = cards;
        this.held = held;
        this.bet = bet;
    }
    public DrawRequest(){}

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Boolean> getHeld() {
        return held;
    }

    public void setHeld(List<Boolean> held) {
        this.held = held;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}