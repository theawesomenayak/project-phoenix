# Unique ID Generator

A distributed unique ID generator based on a simplified version of Twitter's Snowflake algorithm. This ID generator
ensures unique ID generation across distributed systems by utilizing timestamps, node IDs, and sequence numbers.

## Features

- **Distributed Generation**: Safely generate unique IDs across different nodes in a distributed system.
- **High Throughput**: Optimized for generating IDs at a high rate, especially using the caching mechanism.
- **Scalability**: Designed to handle ID generation across many nodes.

## Components

- **Timestamp**: Ensures uniqueness across time and prevents ID collision as time progresses.
- **Node ID**: Allows differentiation between different nodes or servers in the system.
- **Sequence Number**: Allows multiple IDs to be generated within the same millisecond on a node.

## How It Works

Each generated ID is composed of:

- A timestamp component for temporal uniqueness.
- A node ID to ensure uniqueness across different nodes/servers.
- A sequence number to cater to high throughput requirements.

Additionally, this implementation includes a caching mechanism to generate IDs in bulk when the cache runs low. This
enhances performance and reduces the overhead of generating an ID for each request.

## Usage

```java

import com.theawesomenayak.uidgenerator.StandardUniqueIdGenerator;

class MyClass {

    void myMethod() {
        UniqueIDGenerator generator = new StandardUniqueIdGenerator(1);
        long uniqueID = generator.getId();
    }
}
```

## Limitations

- Maximum of 1,024 nodes (can be adjusted with `NODE_ID_BITS`).
- Up to 4,096 unique IDs per node per millisecond (adjustable with `SEQUENCE_BITS`).

## Installation

1. Clone this repository:

```
git clone https://github.com/theawesomenayak/unique-id-generator
```

2. Import the `UniqueIDGenerator` class into your Java project.
3. Initialize and use as demonstrated in the usage section.

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](#) for any open issues.

## License

Distributed under the MIT License. See `LICENSE` for more information.

---