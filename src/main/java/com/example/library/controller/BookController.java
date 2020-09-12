package com.example.library.controller;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController implements CRUDController<Book> {
    private final String path="/books";
    @Autowired
    CRUDService<Book> bookService;

    //create
    @PostMapping(path)
    @Override
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.ok().body(this.bookService.create(book));
    }
    //read
    @GetMapping(path + "/{id}")
    @Override
    public ResponseEntity <Book> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(bookService.getById(id));
    }
    @GetMapping(path)
    @Override
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok().body(bookService.getAll());
    }
    //update
    @PutMapping(path + "/{id}")
    public ResponseEntity <Book> update(@PathVariable Long id, @RequestBody Book book) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(this.bookService.update(book,id));
    }
    //delete
    @DeleteMapping(path + "/{id}")
    @Override
    public HttpStatus delete(@PathVariable Long id) {
        try{
            this.bookService.delete(id);
            return HttpStatus.OK;}
        catch (ResourceNotFoundException e){
            return HttpStatus.NOT_FOUND;
        }
    }
}


