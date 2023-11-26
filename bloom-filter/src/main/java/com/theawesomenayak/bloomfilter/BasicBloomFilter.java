package com.theawesomenayak.bloomfilter;

import java.util.BitSet;
import java.util.function.Function;

public class BasicBloomFilter {

    private final BitSet bitSet; // The bit array
    private final Function<String, Integer>[] hashFunctions; // Array of hash functions

    @SuppressWarnings("unchecked")
    public BasicBloomFilter(int size, int numHashes) {

        this.bitSet = new BitSet(size);
        this.hashFunctions = new Function[numHashes];

        for (int i = 0; i < numHashes; i++) {
            int seed = i;
            hashFunctions[i] = element -> (element.hashCode() ^ seed) % size;
        }
    }

    public void add(String element) {

        for (Function<String, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(element));
            bitSet.set(index);
        }
    }

    public boolean contains(String element) {

        for (Function<String, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(element));
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }
}
