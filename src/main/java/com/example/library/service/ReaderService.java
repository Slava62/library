package com.example.library.service;

import com.example.library.model.Reader;
import com.example.library.repository.ReaderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReaderService {

    @Autowired
    ReaderRepository readerReposirory;

    public List<Reader> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String stringCollection = restTemplate.getForObject("http://localhost:8081/readers", String.class);
        try {
            List<Reader> readerList = objectMapper.readValue(stringCollection, new TypeReference<List<Reader>>() {
            });
            this.readerReposirory.deleteAll();
            this.readerReposirory.saveAll(readerList);
            return this.readerReposirory.findAll();
        } catch (JsonProcessingException e) {
            return this.readerReposirory.findAll();
        }
    }
}
