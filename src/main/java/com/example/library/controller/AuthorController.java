package com.example.library.controller;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    //create
    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        String r= author.getName();
        return ResponseEntity.ok().body(this.authorService.createAuthor(author));
    }
    //read
    @GetMapping("/authors/{id}")
    public ResponseEntity <Author> getReaderById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(authorService.getAuthorById(id));
    }
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllReader() {
        return ResponseEntity.ok().body(authorService.getAllReaders());
    }
    //update
    @PutMapping("/authors/{id}")
    public ResponseEntity <Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(this.authorService.updateAuthor(author,id));
    }
    //delete
    @DeleteMapping("/authors/{id}")
    public HttpStatus deleteReader(@PathVariable Long id) {
        try{
            this.authorService.deleteReader(id);
            return HttpStatus.OK;}
        catch (ResourceNotFoundException e){
            return HttpStatus.NOT_FOUND;
        }
    }
}


