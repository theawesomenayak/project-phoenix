package com.theawesomenayak.generativeai;

import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

import static com.theawesomenayak.generativeai.TestUtils.chatLanguageModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChatAgentTest {

    @Test
    void testChat() {

        ChatAgent chatAgent = AiServices.create(ChatAgent.class, chatLanguageModel());
        String answer = chatAgent.chat("Hello");
        System.out.println(answer);
        assertNotNull(answer);
    }
}