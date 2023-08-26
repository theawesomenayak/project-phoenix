package com.theawesomenayak.strategy;

public interface URLShorteningStrategy {

    String shortenURL(String longURL, int length);
}
