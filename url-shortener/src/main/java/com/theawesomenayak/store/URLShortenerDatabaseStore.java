package com.theawesomenayak.store;

import java.util.Optional;

public class URLShortenerDatabaseStore implements URLShortenerStore {

    @Override
    public void add(String shortUrl, String longUrl) {

        // Save in database
    }

    @Override
    public String getShortUrl(String longUrl) {

        // Get short URL from database
        return null;
    }

    @Override
    public Optional<String> getLongUrl(String shortUrl) {

        // Get long URL from database
        return Optional.empty();
    }
}
