package com.example.library;

import com.example.library.model.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void contextLoads() {
    }

    @Test
    void getRemoteReaderTest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        Reader expected = new Reader();
        //expected.setId(1L);
        expected.setName("Robert Smith");
        expected.setCardid(10045L);
        Assertions.assertEquals(200, restTemplate.postForEntity("http://localhost:8081/readers/",expected,Reader.class).getStatusCode().value());
        String stringCollection = restTemplate.getForObject("http://localhost:8081/readers", String.class);
        //actualCollection.getBody();
        List<Reader> readerList=objectMapper.readValue(stringCollection,new TypeReference<List<Reader>>(){});
       // int lastIndex = actualCollection.size();
        Reader actual = readerList.get(readerList.size()-1);//restTemplate.getForObject("http://localhost:8081/readers/"+lastIndex, Reader.class);
        Assertions.assertTrue(expected.equals(actual));
       // System.out.println("Name:    " + actual.getName());
        //System.out.println("Id:   " + actual.getId());
        //System.out.println("CardId:   " + actual.getCardid());

    }



}
