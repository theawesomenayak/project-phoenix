package com.theawesomenayak.bloomfilter;

import java.util.function.Function;

public class CountingBloomFilter {

    private final int[] countingArray; // The counting array
    private final Function<String, Integer>[] hashFunctions; // Array of hash functions

    @SuppressWarnings("unchecked")
    public CountingBloomFilter(int size, int numHashes) {

        this.countingArray = new int[size];
        this.hashFunctions = new Function[numHashes];

        for (int i = 0; i < numHashes; i++) {
            int seed = i;
            hashFunctions[i] = element -> (element.hashCode() ^ seed) % size;
        }
    }

    public void add(String element) {

        for (Function<String, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(element));
            countingArray[index]++;
        }
    }

    public boolean contains(String element) {

        for (Function<String, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(element));
            if (countingArray[index] == 0) {
                return false;
            }
        }
        return true;
    }
}
