package com.example.library.controller;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Reader;
import com.example.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibController{
    private final String path="/library";
    @Autowired
    LibService libService;
  /*  @Autowired
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
    }*/

    @GetMapping(path +"/b/{id}")
    public ResponseEntity <List<Book>> getBooks(@PathVariable Long id) throws ResourceNotFoundException {
        Long i_d = id;
        return ResponseEntity.ok().body(this.libService.getBooks(id));
    }

    @GetMapping(path +"/r/{id}")
    public ResponseEntity <List<Reader>> getReaders(@PathVariable Long id) throws ResourceNotFoundException {
        Long i_d = id;
        return ResponseEntity.ok().body(this.libService.getReaders(id));
    }
}


