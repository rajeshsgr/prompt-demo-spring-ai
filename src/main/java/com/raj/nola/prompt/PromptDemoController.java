package com.raj.nola.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.AssistantPromptTemplate;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PromptDemoController {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/ai-assistant.st")
    private Resource ytPromptAssistantResource;

    @Autowired
    public PromptDemoController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/simplePrompt/")
    public String simplePrompt(@RequestParam(defaultValue = "java") String inputVal) {

        return chatClient.prompt(new Prompt("Tell me just one fact about " + inputVal)).call().content();

    }


    @GetMapping("/simpleUserPrompt")
    public String simpleUserPrompt() {

        String userText = """
                 Tell me about 2 famous book and a small summary.
                  Write at least a sentence for each book.
                """;

        Message userMessage = new UserMessage(userText);

        return chatClient.prompt(new Prompt(userMessage)).call().content();

    }

    @GetMapping("/simpleUserSystemPrompt")
    public String simpleUserSystemPrompt() {

        String userText = """
                 Tell me about 2 famous book and a small summary.
                  Write at least a sentence for each book.
                """;
        Message userMessage = new UserMessage(userText);

        String systemText = """
                You are a helpful AI assistant that helps people find information.
                Your name is Rajesh
                You should reply to the user's request with your name and a greeting.
                """;

        Message systemMessage = new SystemPromptTemplate(systemText).createMessage();

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return chatClient.prompt(prompt).call().content();
    }

    @GetMapping("/simpleUserAssistantPrompt")
    public String simpleUserAssistantPrompt() {

        String userText = "Me and my friend went to the store.";

        Message userMessage = new UserMessage("Is this sentence correct: " + userText);

        String systemText = """
                You are a helpful AI assistant that .
                Your name is Rajesh
                You should reply to the user's request with your name and a greeting.
                """;

        Message systemMessage = new SystemPromptTemplate(systemText).createMessage();

        String instructions = """
                You are a helpful AI assistant that helps correct grammar mistakes.
                You should provide feedback for the sentence: {text} 
                and correct if there are any issues.
                """;

        AssistantPromptTemplate assistantPromptTemplate = new AssistantPromptTemplate(instructions);

        Message assistantPromptTemplateMessage = assistantPromptTemplate.createMessage(Map.of("text", userText));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage, assistantPromptTemplateMessage));

        return chatClient.prompt(prompt).call().content();
    }

    @GetMapping("/simpleUserAssistantResPrompt")
    public String simpleUserAssistantResPrompt() {

        String userText = """
                 Tell me about 2 famous book and a small summary.
                  Write at least a sentence for each book.
                """;

        UserMessage userMessage = new UserMessage(userText);

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(ytPromptAssistantResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "AI Chat Bot"));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return chatClient.prompt(prompt).call().content();

    }

}
