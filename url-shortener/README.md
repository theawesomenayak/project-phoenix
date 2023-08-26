# URL Shortener

URL shortening is a crucial tool in modern web applications, allowing for the generation of compact URL aliases for
lengthy links. This repository provides implementations for two simple URL shorteners:

1. **MD5-Based Shortener**: Utilizes the MD5 hashing function to produce a deterministic short URL.
2. **Character Timestamp-Based Shortener**: Employs a combination of alphabetical characters, numbers, and the current
   timestamp to generate unique short URLs.

## Features

- **Variety**: Multiple URL shortening algorithms for different use-cases.
- **Fixed Length**: Both methods produce URLs of a fixed length, ensuring predictability in the URL structure.
- **Collision Handling**: Both methods are designed with mechanisms to handle and reduce potential collisions.

## How They Work

1. **MD5-Based Shortener**:
    - The long URL is hashed using the MD5 hashing function.
    - A fixed number of characters from the hashed output are then used as the shortened URL.

2. **Character Timestamp-Based Shortener**:
    - A combination of randomly selected characters from a predefined character set and the current timestamp is used.
    - This approach ensures a higher degree of uniqueness for each generated URL.

## Usage

```java
import com.example.urlshortener.core.URLShortener;
import com.example.urlshortener.store.URLShortenerMemoryStore;
import com.example.urlshortener.store.URLShortenerStore;
import com.example.urlshortener.strategy.CharacterTimestampStrategy;
import com.example.urlshortener.strategy.URLShorteningStrategy;

public class MyClass {

    public void urlShorteningExample() {

        URLShorteningStrategy urlShorteningStrategy = new CharacterTimestampStrategy();
        URLShortenerStore urlShortenerStore = new URLShortenerMemoryStore();
        URLShortener shortener = new URLShortener(urlShorteningStrategy, urlShortenerStore);

        String longURL = "https://www.example.com";
        String shortURL = shortener.shortenURL(longURL, 10);
        System.out.println("Shortened URL: " + shortURL);

        String longURLFromShort = shortener.getLongURL(shortURL);
        System.out.println("Original URL: " + longURLFromShort);
    }
}
```

## Installation

1. Clone this repository:

```
git clone https://github.com/theawesomenayak/project-phoenix
```

2. Import the `URLShortener` class into your Java project.
3. Initialize and use as demonstrated in the usage section.

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](#) for any open issues.

## License

Distributed under the MIT License. See `LICENSE` for more information.

---