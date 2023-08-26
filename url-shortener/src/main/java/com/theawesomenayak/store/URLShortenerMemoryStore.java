package com.theawesomenayak.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class URLShortenerMemoryStore implements URLShortenerStore {

    private final Map<String, String> longToShort;
    private final Map<String, String> shortToLong;

    public URLShortenerMemoryStore() {

        longToShort = new HashMap<>();
        shortToLong = new HashMap<>();
    }

    @Override
    public void add(String shortUrl, String longUrl) {

        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);
    }

    @Override
    public String getShortUrl(String longUrl) {

        return longToShort.get(longUrl);
    }

    @Override
    public Optional<String> getLongUrl(String shortUrl) {

        return shortToLong.get(shortUrl).describeConstable();
    }
}
