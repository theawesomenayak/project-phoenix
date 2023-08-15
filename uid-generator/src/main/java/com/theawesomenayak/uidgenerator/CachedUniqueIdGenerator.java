package com.theawesomenayak.uidgenerator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedUniqueIdGenerator extends StandardUniqueIdGenerator {

    // Cache of pre-generated IDs
    private Queue<Long> activeQueue = new LinkedList<>();
    private Queue<Long> standbyQueue = new LinkedList<>();
    private final int cacheFillCount; // Number of IDs to generate in bulk
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CachedUniqueIdGenerator(int nodeId, int cacheFillCount) {
        super(nodeId);
        this.cacheFillCount = cacheFillCount;
        refillCache(standbyQueue);
    }

    // Pop ID from cache or generate if needed
    @Override
    public synchronized Long generate() {
        if (activeQueue.isEmpty()) {
            // Swap the queues
            Queue<Long> temp = activeQueue;
            activeQueue = standbyQueue;
            standbyQueue = temp;

            // Asynchronously refill the standby queue
            executorService.submit(() -> refillCache(standbyQueue));
        }
        return activeQueue.poll();
    }

    // Refill the ID cache
    private void refillCache(Queue<Long> queue) {
        for (int i = 0; i < cacheFillCount; i++) {
            queue.add(super.generate());
        }
    }
}
