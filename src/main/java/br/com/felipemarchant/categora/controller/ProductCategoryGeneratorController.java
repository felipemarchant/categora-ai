package br.com.felipemarchant.categora.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class ProductCategoryGeneratorController {

    private final ChatClient chatClient;

    public ProductCategoryGeneratorController(final ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String category(final String product) {
        var system = """
        You are a product categorizer and should only respond with the product category.
        Category has unique id, name, description, created at, updated at
        Return array objects
        Return only json format in your response
        Json must not pretty
        
        Choose a category from the list below:
        
        1. Personal Hygiene
        2. Electronics
        3. Sports
        4. Others
        
        ###### example of usage:
        
        Question: Soccer ball
        Answer: [{"id":1,"name":"Sports","description":"Sport description","createdAt":"2025-01-21T10:10:00Z","updatedAt":"2025-01-21T10:10:00Z"}]
        """;

        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system(system)
                .user(product)
                .options(ChatOptionsBuilder
                        .builder()
                        .withTemperature(0.8)
                        .build())
                .call()
                .content();
    }
}
