package com.theawesomenayak;

import com.theawesomenayak.store.URLShortenerStore;
import com.theawesomenayak.strategy.URLShorteningStrategy;

public class URLShortener {

    private final URLShorteningStrategy urlShorteningStrategy;

    private final URLShortenerStore urlShortenerStore;

    public URLShortener(URLShorteningStrategy urlShorteningStrategy, URLShortenerStore urlShortenerStore) {
        this.urlShorteningStrategy = urlShorteningStrategy;
        this.urlShortenerStore = urlShortenerStore;
    }

    public String shortenURL(String longURL, int length) {

        String shortUrl = urlShortenerStore.getShortUrl(longURL);
        if (null != shortUrl) {
            return shortUrl;
        }
        String shortURL = urlShorteningStrategy.shortenURL(longURL, length);
        urlShortenerStore.add(shortURL, longURL);
        return shortURL;

    }

    public String getLongURL(String shortURL) {

        return urlShortenerStore.getLongUrl(shortURL).orElse(null);
    }
}
