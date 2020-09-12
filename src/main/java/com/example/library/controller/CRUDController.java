package com.example.library.controller;

import com.example.library.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CRUDController<T> {
    //create
   // @PostMapping("/books")
    ResponseEntity<T> create(@RequestBody T body);

    //read
    //@GetMapping("/books/{id}")
    ResponseEntity<T> getById(@PathVariable Long id) throws ResourceNotFoundException;

    //@GetMapping("/books")
    ResponseEntity<List<T>> getAll();

    //update
    //@PutMapping("/books/{id}")
    ResponseEntity<T> update(@PathVariable Long id, @RequestBody T body) throws ResourceNotFoundException;

    //delete
    //@DeleteMapping("/books/{id}")
    HttpStatus delete(@PathVariable Long id);
}
