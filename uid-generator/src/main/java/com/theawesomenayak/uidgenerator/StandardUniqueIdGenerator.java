package com.theawesomenayak.uidgenerator;

public class StandardUniqueIdGenerator implements UniqueIdGenerator {

    // Constants representing the number of bits for each component of the ID.
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    // Maximum possible node IDs and sequence numbers given the number of bits allocated.
    private static final int MAX_NODE_ID = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int MAX_SEQUENCE = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    // Bit shift positions for each component of the ID.
    private static final int NODE_ID_SHIFT = SEQUENCE_BITS;
    private static final int TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;

    // Node ID for the current machine/node.
    private final int nodeId;

    // Variables to keep track of the last generated ID's timestamp and sequence number.
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public StandardUniqueIdGenerator(int nodeId) {
        // Validate that the node ID is within the allowable range.
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException(String.format("Node ID must be between 0 and %d", MAX_NODE_ID));
        }
        this.nodeId = nodeId;
    }

    // Generate a unique ID.
    @Override
    public synchronized Long generate() {
        // Get the current timestamp.
        long timestamp = System.currentTimeMillis();

        // Ensure that the clock hasn't moved backwards.
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id.");
        }

        // If generating more than one ID in the same millisecond, increment the sequence number.
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // If sequence number overflows, wait for the next millisecond.
            if (sequence == 0) {
                timestamp = waitForNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        // Combine the timestamp, node ID, and sequence number to generate a unique ID.
        return ((timestamp) << TIMESTAMP_SHIFT) | ((long) nodeId << NODE_ID_SHIFT) | sequence;
    }

    // Wait until the next millisecond.
    private long waitForNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
