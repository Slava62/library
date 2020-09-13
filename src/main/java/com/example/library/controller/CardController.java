package com.example.library.controller;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Card;
import com.example.library.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController implements CRUDController<Card> {
    private final String path="/cards";

    @Autowired
    CRUDService<Card> cardService;

    //create
    @Override
    @PostMapping(path)
    public ResponseEntity<Card> create(@RequestBody Card card) {
        return ResponseEntity.ok().body(this.cardService.create(card));
    }
    //read
    @Override
    @GetMapping(path+"/{bookid}")
    public ResponseEntity <Card> getById(@PathVariable Long bookid) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(cardService.getById(bookid));
    }
    @Override
    @GetMapping(path)
    public ResponseEntity<List<Card>> getAll() {
        return ResponseEntity.ok().body(cardService.getAll());
    }
    //update
    @Override
    @PutMapping(path +"/{bookid}")
    public ResponseEntity <Card> update(@PathVariable Long bookid, @RequestBody Card card) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(this.cardService.update(card,bookid));
    }
    //delete
    @Override
    @DeleteMapping(path +"/{bookid}")
    public HttpStatus delete(@PathVariable Long bookid) {
        try{
            this.cardService.delete(bookid);
            return HttpStatus.OK;}
        catch (ResourceNotFoundException e){
            return HttpStatus.NOT_FOUND;
        }
    }
}


