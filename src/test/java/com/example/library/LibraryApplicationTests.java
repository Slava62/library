package com.example.library;

import com.example.library.model.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LibraryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test

    void getRemoteReaderTest() {
        RestTemplate restTemplate = new RestTemplate();
        Reader expected = new Reader();
        expected.setId(1L);
        expected.setName("Nasha Masha");
        expected.setCardid(12345L);
        Assertions.assertEquals(200, restTemplate.postForEntity("http://localhost:8080/readers/",expected,Reader.class).getStatusCode().value());
        List<Reader> actualCollection = restTemplate.getForObject("http://localhost:8080/readers", ArrayList.class);
        int lastIndex = actualCollection.size()-1;
        Reader actual = restTemplate.getForObject("http://localhost:8080/readers/"+lastIndex, Reader.class);
        Assertions.assertTrue(expected.equals(actual));
        System.out.println("Name:    " + actual.getName());
        System.out.println("Id:   " + actual.getId());
        System.out.println("CardId:   " + actual.getCardid());
       // System.out.println("Website: " + reader.getWebsite());
    }
}
