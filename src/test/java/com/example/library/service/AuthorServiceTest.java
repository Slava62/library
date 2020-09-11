package com.example.library.service;

import com.example.library.model.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
class AuthorServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAuthorTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Author author=new Author(1L,"Pushkin A.S.");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        int actual = result.andReturn().getResponse().getStatus();
        int expected = 200;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateAuthorTest() {
    }
}