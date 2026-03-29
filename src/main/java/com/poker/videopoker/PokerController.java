package com.poker.videopoker;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://videopoker-frontend.onrender.com")
@RestController
@RequestMapping("/poker")
public class PokerController {

    // DEAL → get 5 new cards
    @GetMapping("/deal")
    public List<Card> deal() {
        Deck deck = new Deck();
        deck.shuffle();
        Hand hand = new Hand(deck);
        return hand.getCards();
    }

    // EVALUATE → check hand ranking + payout
    @PostMapping("/evaluate")
    public Map<String, Object> evaluate(@RequestBody DrawRequest request) {

        List<Card> cards = request.getCards();
        int bet = request.getBet();

        Hand hand = new Hand(cards);
        String result = hand.evaluateHand();
        int payout = hand.getPayout(result, bet);

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        response.put("payout", payout);

        return response;
    }

    // DRAW → replace ONLY non-held cards
    @PostMapping("/draw")
    public List<Card> draw(@RequestBody DrawRequest request) {

        List<Card> cards = request.getCards();
        List<Boolean> held = request.getHeld();

        if (cards == null || held == null || cards.size() != 5 || held.size() != 5) {
            throw new RuntimeException("Invalid draw request");
        }

        Deck deck = new Deck();
        deck.shuffle();
        deck.getCards().removeAll(cards);


        for (int i = 0; i < 5; i++) {
            if (!held.get(i)) {
                cards.set(i, deck.drawCard());
            }
        }

        return cards;
    }
}