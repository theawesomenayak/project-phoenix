# Bloom Filter

This repository contains simple implementations of different types of Bloom Filters in Java. Bloom Filters are
probabilistic data structures used to test whether an element is a member of a set. Three types of Bloom Filters are
included: Basic Bloom Filter, Counting Bloom Filter, and Scalable Bloom Filter.

## Usage

### Basic Bloom Filter

The `BasicBloomFilter` is a standard implementation of a Bloom Filter. It utilizes a fixed-size bit array and multiple
hash functions to efficiently represent and query set membership.

```java
// Example Usage
public class MyClass {

    public void basicBloomFiler() {

        BasicBloomFilter bloomFilter = new BasicBloomFilter(100, 3);
        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");
        System.out.println("Is 'apple' likely in the set? " + bloomFilter.contains("apple"));
    }
}
```

### Counting Bloom Filter

The `CountingBloomFilter` is an extension of the basic Bloom Filter that keeps track of the number of times an element
is added. This allows for element removal and supports applications where counting occurrences is crucial.

```java
// Example Usage
public class MyClass {

    public void countingBloomFilter() {

        CountingBloomFilter bloomFilter = new CountingBloomFilter(100, 3);
        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");
        System.out.println("Is 'apple' likely in the set? " + bloomFilter.contains("apple"));
    }
}
```

### Scalable Bloom Filter

The `ScalableBloomFilter` is designed to dynamically scale as the number of elements in the set grows. It employs an
array of Bloom Filters and adds a new filter when the current one reaches a predefined capacity.

```java
// Example Usage
public class MyClass {

    public void scalableBloomFilter() {

        ScalableBloomFilter bloomFilter = new ScalableBloomFilter(100, 3, 3);
        bloomFilter.add("apple");
        bloomFilter.add("orange");
        bloomFilter.add("banana");
        System.out.println("Is 'apple' likely in the set? " + bloomFilter.contains("apple"));
    }
}
```

## Getting Started

1. Clone this repository: `git clone https://github.com/theawesomenayak/project-phoenix.git`
2. Import the appropriate bloom filter class into your Java project.
3. Initialize and use as demonstrated in the usage section.

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](#) for any open issues.

## License

Distributed under the MIT License. See `LICENSE` for more information.

---