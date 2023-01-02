package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards {
    public enum Suit {
        Diamonds, Hearts, Spades, Clubs
    }

    public enum Value {
        V_6, V_7, V_8
    }

    public static class Card {
        private Suit suit;
        private Value value;

        public Card(Suit suit, Value value) {
            this.suit = suit;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        List<Card> cardDeck = Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Value.values())
                .map(value -> new Card(suit, value)))
                .collect(Collectors.toList());
    }
}
