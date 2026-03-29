package com.poker.videopoker;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private ArrayList<Card> cards;
    public Hand(Deck deck){
        cards = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            cards.add(deck.drawCard());
        }
    }

    public Hand(List<Card> cards){
        this.cards = new ArrayList<>(cards);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public boolean isRoyalFlush(){
        String suit = cards.get(0).getSuit();
        boolean has10 = false, hasJ = false, hasQ = false, hasK = false, hasA = false;

        for (Card c : cards){
            if (!c.getSuit().equals(suit)) return false;
            String rank = c.getRank();
            if (rank.equals("10")) has10 = true;
            else if (rank.equals("J")) hasJ = true;
            else if (rank.equals("Q")) hasQ = true;
            else if (rank.equals("K")) hasK = true;
            else if (rank.equals("A")) hasA = true;
        }

        return has10 && hasJ && hasQ && hasK && hasA;
    }

    public boolean isStraightFlush(){
        return isStraight() && isFlush();
    }

    public boolean isFourOfAKind() {
        for (int i = 0; i < cards.size(); i++) {
            String rank = cards.get(i).getRank();
            int count = 0;

            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(j).getRank().equals(rank)) {
                    count++;
                }
            }
            if (count == 4) return true;
        }
        return false;
    }

    public boolean isFullHouse(){
        boolean hasThree = false;
        boolean hasTwo = false;

        for (int i = 0; i < cards.size(); i++){
            String rank = cards.get(i).getRank();
            int count = 0;

            for (int j = 0; j < cards.size(); j++){
                if (cards.get(j).getRank().equals(rank)){
                    count++;
                }
            }

            if (count == 3) hasThree = true;
            if (count == 2) hasTwo = true;
        }

        return hasThree && hasTwo;
    }

    public boolean isFlush(){
        String suit = cards.get(0).getSuit();

        for (Card c : cards){
            if (!c.getSuit().equals(suit)){
                return false;
            }
        }
        return true;
    }

    public boolean isStraight() {
        int[] values = new int[cards.size()];

        for (int i = 0; i < cards.size(); i++) {
            String rank = cards.get(i).getRank();

            if (rank.equals("A")) values[i] = 14;
            else if (rank.equals("K")) values[i] = 13;
            else if (rank.equals("Q")) values[i] = 12;
            else if (rank.equals("J")) values[i] = 11;
            else values[i] = Integer.parseInt(rank);
        }

        java.util.Arrays.sort(values);

        boolean consecutive = true;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i + 1] != values[i] + 1) {
                consecutive = false;
                break;
            }
        }

        if (consecutive) return true;

        return values[0] == 2 &&
                values[1] == 3 &&
                values[2] == 4 &&
                values[3] == 5 &&
                values[4] == 14;
    }

    public boolean isThreeOfAKind(){
        for (int i = 0; i < cards.size(); i++) {
            String rank = cards.get(i).getRank();
            int count = 0;

            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(j).getRank().equals(rank)) {
                    count++;
                }
            }
            if (count == 3) return true;
        }
        return false;
    }

    public boolean isTwoPair(){
        int pairCount = 0;

        for (int i = 0; i < cards.size(); i++){
            for (int j = i + 1; j < cards.size(); j++){
                String rank1 = cards.get(i).getRank();
                String rank2 = cards.get(j).getRank();

                if (rank1.equals(rank2)){
                    pairCount++;
                }
            }
        }

        return pairCount == 2;
    }

    public boolean isOnePair(){
        for (int i = 0; i < cards.size(); i++){
            for (int j = i + 1; j < cards.size(); j++){
                String rank1 = cards.get(i).getRank();
                String rank2 = cards.get(j).getRank();

                if (rank1.equals(rank2)){
                    if (rank1.equals("J") || rank1.equals("Q") ||
                            rank1.equals("K") || rank1.equals("A")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public int getPayout(String handName, int bet) {
        int[][] payTable = {
                {250, 500, 750, 1000, 4000}, // ROYAL FLUSH
                {50, 100, 150, 200, 250},    // STRAIGHT FLUSH
                {25, 50, 75, 100, 125},      // FOUR OF A KIND
                {9, 18, 27, 36, 45},         // FULL HOUSE
                {6, 12, 18, 24, 30},         // FLUSH
                {4, 8, 12, 16, 20},          // STRAIGHT
                {3, 6, 9, 12, 15},           // THREE OF A KIND
                {2, 4, 6, 8, 10},            // TWO PAIR
                {1, 2, 3, 4, 5}              // JACKS OR BETTER
        };

        String[] hands = {
                "ROYAL FLUSH",
                "STRAIGHT FLUSH",
                "FOUR OF A KIND",
                "FULL HOUSE",
                "FLUSH",
                "STRAIGHT",
                "THREE OF A KIND",
                "TWO PAIR",
                "JACKS OR BETTER"
        };

        for (int i = 0; i < hands.length; i++) {
            if (hands[i].equals(handName)) {
                return payTable[i][bet - 1];
            }
        }

        return 0;
    }

    public String evaluateHand(){
        if (isRoyalFlush()) return "ROYAL FLUSH";
        else if (isStraightFlush()) return "STRAIGHT FLUSH";
        else if (isFourOfAKind()) return "FOUR OF A KIND";
        else if (isFullHouse()) return "FULL HOUSE";
        else if (isFlush()) return "FLUSH";
        else if (isStraight()) return "STRAIGHT";
        else if (isThreeOfAKind()) return "THREE OF A KIND";
        else if (isTwoPair()) return "TWO PAIR";
        else if (isOnePair()) return "JACKS OR BETTER";
        else return "HIGH CARD";
    }
}