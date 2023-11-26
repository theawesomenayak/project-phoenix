package com.theawesomenayak.bloomfilter;

import java.util.BitSet;

public class ScalableBloomFilter {

    private final int size; // Size of the bit array
    private final int numHashes; // Number of hash functions
    private final int numFilters; // Number of filters in the array
    private final BitSet[] filters; // Array of bit arrays

    public ScalableBloomFilter(int initialSize, int initialNumHashes, int initialNumFilters) {

        this.size = initialSize;
        this.numHashes = initialNumHashes;
        this.numFilters = initialNumFilters;
        this.filters = new BitSet[numFilters];

        for (int i = 0; i < numFilters; i++) {
            filters[i] = new BitSet(size);
        }
    }

    public void add(String element) {

        for (int i = 0; i < numHashes; i++) {
            int index = Math.abs((element.hashCode() ^ i) % size);
            filters[numFilters - 1].set(index);
        }
    }

    public boolean contains(String element) {

        for (BitSet filter : filters) {
            boolean inCurrentFilter = true;
            for (int i = 0; i < numHashes; i++) {
                int index = Math.abs((element.hashCode() ^ i) % size);
                if (!filter.get(index)) {
                    inCurrentFilter = false;
                    break;
                }
            }
            if (inCurrentFilter) {
                return true;
            }
        }
        return false;
    }
}
