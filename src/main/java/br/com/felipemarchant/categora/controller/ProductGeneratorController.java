package br.com.felipemarchant.categora.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductGeneratorController {

    private final ChatClient chatClient;

    public ProductGeneratorController(final ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String generateProduct() {
        final String userPrompt = """
            Generate 5 products with theses criterias:
            Product has unique id, name, description, price, created at, updated at
            Return array objects
            Return only json format in your response
            Json must not pretty
        """;
        return chatClient.prompt()
                .user(userPrompt)
                .call()
                .content();
    }
}
