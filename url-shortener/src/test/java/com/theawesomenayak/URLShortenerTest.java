package com.theawesomenayak;

import com.theawesomenayak.store.URLShortenerMemoryStore;
import com.theawesomenayak.store.URLShortenerStore;
import com.theawesomenayak.strategy.CharacterTimestampStrategy;
import com.theawesomenayak.strategy.MD5HashStrategy;
import com.theawesomenayak.strategy.URLShorteningStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class URLShortenerTest {

    private static final String LONG_URL = "https://www.example.com";

    @Test
    void shortenURLUsingMD5Hash() {
        URLShorteningStrategy urlShorteningStrategy = new MD5HashStrategy();
        URLShortenerStore urlShortenerStore = new URLShortenerMemoryStore();
        URLShortener shortener = new URLShortener(urlShorteningStrategy, urlShortenerStore);

        String shortURL = shortener.shortenURL(LONG_URL, 10);
        assertEquals(10, shortURL.length());

        String longURLFromShort = shortener.getLongURL(shortURL);
        assertEquals(LONG_URL, longURLFromShort);
    }

    @Test
    void shortenURLUsingCharacterTimestamp() {
        URLShorteningStrategy urlShorteningStrategy = new CharacterTimestampStrategy();
        URLShortenerStore urlShortenerStore = new URLShortenerMemoryStore();
        URLShortener shortener = new URLShortener(urlShorteningStrategy, urlShortenerStore);

        String shortURL = shortener.shortenURL(LONG_URL, 10);
        assertEquals(10, shortURL.length());

        String longURLFromShort = shortener.getLongURL(shortURL);
        assertEquals(LONG_URL, longURLFromShort);
    }
}