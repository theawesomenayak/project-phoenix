package com.theawesomenayak.bloomfilter;

import org.junit.jupiter.api.Test;

class BloomFilterTest {

    @Test
    void testBasicBloomFilter() {

        BasicBloomFilter bloomFilter = new BasicBloomFilter(100, 3);

        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");

        System.out.println("Is 'apple' likely in the set? " + bloomFilter.contains("apple"));
        System.out.println("Is 'grape' likely in the set? " + bloomFilter.contains("grape"));
    }

    @Test
    void testBasicCountingFilter() {

        CountingBloomFilter bloomFilter = new CountingBloomFilter(100, 3);

        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");

        System.out.println("Is 'apple' likely in the set? " + bloomFilter.contains("apple"));
        System.out.println("Is 'grape' likely in the set? " + bloomFilter.contains("grape"));
    }

    @Test
    void testBasicScalableFilter() {

        ScalableBloomFilter bloomFilter = new ScalableBloomFilter(100, 3, 3);

        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");

        System.out.println("Is 'apple' likely in the set? " + bloomFilter.contains("apple"));
        System.out.println("Is 'grape' likely in the set? " + bloomFilter.contains("grape"));
    }
}