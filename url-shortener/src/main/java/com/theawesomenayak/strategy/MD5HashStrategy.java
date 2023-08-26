package com.theawesomenayak.strategy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashStrategy implements URLShorteningStrategy {

    @Override
    public String shortenURL(String longURL, int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(longURL.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest).substring(0, length);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
