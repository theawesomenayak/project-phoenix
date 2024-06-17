package com.theawesomenayak.generativeai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.Test;

import static com.theawesomenayak.generativeai.TestUtils.chatLanguageModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HelloWorldTest {

    @Test
    void testHello() {

        ChatLanguageModel chatLanguageModel = chatLanguageModel();
        String answer = chatLanguageModel.generate("Hello");
        System.out.println(answer);
        assertNotNull(answer);
    }
}