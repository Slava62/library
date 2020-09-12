package com.example.library.controller;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController implements CRUDController<Author> {
    private final String path="/authors";
    @Autowired
    CRUDService<Author> authorService;

    //create
    @Override
    @PostMapping(path)
    public ResponseEntity<Author> create(@RequestBody Author author) {
        return ResponseEntity.ok().body(this.authorService.create(author));
    }
    //read
    @Override
    @GetMapping(path+"/{id}")
    public ResponseEntity <Author> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(authorService.getById(id));
    }
    @Override
    @GetMapping(path)
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok().body(authorService.getAll());
    }
    //update
    @Override
    @PutMapping(path +"/{id}")
    public ResponseEntity <Author> update(@PathVariable Long id, @RequestBody Author author) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(this.authorService.update(author,id));
    }
    //delete
    @Override
    @DeleteMapping(path +"/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        try{
            this.authorService.delete(id);
            return HttpStatus.OK;}
        catch (ResourceNotFoundException e){
            return HttpStatus.NOT_FOUND;
        }
    }
}


