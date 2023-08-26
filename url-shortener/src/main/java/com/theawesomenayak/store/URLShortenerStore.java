package com.theawesomenayak.store;

import java.util.Optional;

public interface URLShortenerStore {

    void add(String shortUrl, String longUrl);

    String getShortUrl(String longUrl);

    Optional<String> getLongUrl(String shortUrl);
}
