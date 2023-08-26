package com.theawesomenayak.strategy;

import java.time.Instant;
import java.util.Random;

public class CharacterTimestampStrategy implements URLShorteningStrategy {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public String shortenURL(String longURL, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        Instant now = Instant.now();
        long timestamp = now.toEpochMilli();

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                int index = random.nextInt(CHARACTERS.length());
                sb.append(CHARACTERS.charAt(index));
            } else {
                int digit = (int) (timestamp % 10);
                sb.append(digit);
                timestamp /= 10;
            }
        }

        return sb.toString();
    }
}
