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
        return chatClient.prompt()
                .user("Generate 5 products")
                .call()
                .content();
    }
}
