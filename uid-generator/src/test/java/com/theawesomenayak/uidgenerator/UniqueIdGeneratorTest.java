package com.theawesomenayak.uidgenerator;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniqueIdGeneratorTest {

    private static final int GENERATION_SIZE = 1000000;

    @Test
    void generateStandardUniqueIds() {
        UniqueIdGenerator uniqueIdGenerator = new StandardUniqueIdGenerator(1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < GENERATION_SIZE; i++) {
            uniqueIdGenerator.generate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    void generateCachedUniqueIds() {
        UniqueIdGenerator uniqueIdGenerator = new CachedUniqueIdGenerator(1, 100000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < GENERATION_SIZE; i++) {
            uniqueIdGenerator.generate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void testIdGenerationFromMultipleNodes() throws Exception {
        // Number of IDs to generate from each node
        final int ID_COUNT_PER_NODE = 1000;

        // Use ExecutorService to simulate multiple nodes
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Callable tasks representing each node's ID generation
        Callable<Set<Long>> node1Task = () -> generateIdsForNode(new StandardUniqueIdGenerator(1), ID_COUNT_PER_NODE);
        Callable<Set<Long>> node2Task = () -> generateIdsForNode(new StandardUniqueIdGenerator(2), ID_COUNT_PER_NODE);
        Callable<Set<Long>> node3Task = () -> generateIdsForNode(new StandardUniqueIdGenerator(3), ID_COUNT_PER_NODE);

        // Submit tasks and gather results
        Future<Set<Long>> future1 = executor.submit(node1Task);
        Future<Set<Long>> future2 = executor.submit(node2Task);
        Future<Set<Long>> future3 = executor.submit(node3Task);

        Set<Long> allIds = new HashSet<>();
        allIds.addAll(future1.get());
        allIds.addAll(future2.get());
        allIds.addAll(future3.get());

        // Ensure no collision by checking the combined set size
        int totalGeneratedIds = 3 * ID_COUNT_PER_NODE;
        assertEquals(allIds.size(), totalGeneratedIds);
    }

    private Set<Long> generateIdsForNode(UniqueIdGenerator generator, int count) {
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < count; i++) {
            ids.add(generator.generate());
        }
        return ids;
    }
}
