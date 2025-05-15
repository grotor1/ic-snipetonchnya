package com.grotor.snipetochnya;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateReadUpdateReadDeletePost() throws Exception {
        // Create a post
        UUID postId = UUID.randomUUID();
        mockMvc.perform(post("/posts")
                        .contentType("application/json")
                        .content("{\"title\": \"Test Post\", \"content\": \"This is a test post\"}")
                )
                .andExpect(status().isOk());

        // Read the post
        mockMvc.perform(get("/posts/{id}", postId))
                .andExpect(status().isOk());

        // Update the post
        mockMvc.perform(patch("/posts/{id}", postId)
                        .contentType("application/json")
                        .content("{\"title\": \"Updated Test Post\"}")
                )
                .andExpect(status().isOk());

        // Read the updated post
        mockMvc.perform(get("/posts/{id}", postId))
                .andExpect(status().isOk());

        // Delete the post
        mockMvc.perform(delete("/posts/{id}", postId))
                .andExpect(status().isNoContent());
    }
}