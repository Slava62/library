package com.example.library.service;

import com.example.library.model.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Author author=new Author(1L,"Pushkin A.S.");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Author actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        Author expected = author;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByIdTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Author author=new Author(1L,"Pushkin A.S.");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Author actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        author.setId(actual.getId());
        result = mockMvc.perform(MockMvcRequestBuilders
                .get("/authors/" + actual.getId())
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        Author expected = author;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Author author1=new Author(1L,"Pushkin A.S.");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author1))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Author author2=new Author(2L,"Gogol N.V.");
        result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author2))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result = mockMvc.perform(MockMvcRequestBuilders
                .get("/authors")
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        List<Author> authorList = objectMapper.readValue(result.andReturn()
                .getResponse().getContentAsString(),new TypeReference<List<Author>>(){});
        if(authorList.size()==2) {
            Author actual = authorList.get(0);
            Assertions.assertTrue(author1.equals(actual));
            actual = authorList.get(1);
            Assertions.assertTrue(author2.equals(actual));
        } else {Assertions.fail();}
    }

    @Test
    void updateTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Author author=new Author(1L,"Pushkin A.S.");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Author actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        author.setName("Gogol N.V.");
        author.setId(actual.getId());
        result = mockMvc.perform(MockMvcRequestBuilders
                .put("/authors/" + actual.getId())
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        Author expected = author;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Author author=new Author(1L,"Pushkin A.S.");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(objectMapper.writerFor(Author.class)
                        .writeValueAsString(author))
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Author actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/authors/" + actual.getId())
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result = mockMvc.perform(MockMvcRequestBuilders
                .get("/authors/" + actual.getId())
                .contentType("application/json"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        actual = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),Author.class);
        Author expected = author;
        Assertions.assertNotEquals(expected, actual);
    }
}