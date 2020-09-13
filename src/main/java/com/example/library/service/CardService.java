package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Card;
import com.example.library.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardService implements CRUDService<Card>{

    @Autowired
    CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }
    @Override
    public Card update(Card card, Long bookid){
        Optional<Card> cardDb = this.cardRepository.findById(bookid);

        if (cardDb.isPresent()) {
            Card cardUpdate = cardDb.get();
            cardUpdate.setReaderid(card.getReaderid());
            cardRepository.save(cardUpdate);
            return cardUpdate;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Record not found with id : " + bookid);
            return new Card(0L,0L);
            // throw new ResourceNotFoundException("Record not found with id : " + author.getId().toString());
        }
    }
    @Override
    public Card getById(Long bookId) {
        Optional <Card> cardDb = this.cardRepository.findById(bookId);
        if (cardDb.isPresent()) {
            return cardDb.get();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Record not found with id : " + bookId);
            return new Card(0L,0L);
            //throw new ResourceNotFoundException("Record not found with id : " + authorId);
        }
    }
    @Override
    public List<Card> getAll() {
        return this.cardRepository.findAll();
    }
    @Override
    public void delete(Long bookId) {
        Optional <Card> cardDb = this.cardRepository.findById(bookId);

        if (cardDb.isPresent()) {
            this.cardRepository.delete(cardDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + bookId);
        }

    }
}
