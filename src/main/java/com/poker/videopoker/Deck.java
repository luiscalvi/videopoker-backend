package com.poker.videopoker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;
    private int currentIndex;

    public Deck() {
        cards = new ArrayList<>();

        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = {"HEARTS","DIAMONDS","CLUBS","SPADES"};

        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                cards.add(new Card(ranks[i], suits[j]));
            }
        }

        currentIndex = 0;
    }

    public void shuffle() {
        Collections.shuffle(cards);
        currentIndex = 0;
    }

    public Card drawCard() {
        if (currentIndex >= cards.size()) {
            throw new RuntimeException("No more cards in deck");
        }

        return cards.get(currentIndex++);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}