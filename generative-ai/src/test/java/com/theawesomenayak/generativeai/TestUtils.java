package com.theawesomenayak.generativeai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class TestUtils {

    public static ChatLanguageModel chatLanguageModel() {

        return new OllamaChatModel.OllamaChatModelBuilder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3")
                .temperature(0.1)
                .build();
    }
}
