package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Card;
import com.example.library.model.Reader;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CardRepository;
import com.example.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibService{

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    CardRepository cardRepository;

    /*@Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }
    @Override
    public Author update(Author author, Long id){
        Optional<Author> authorDb = this.authorRepository.findById(id);

        if (authorDb.isPresent()) {
            Author authorUpdate = authorDb.get();
            authorUpdate.setName(author.getName());
            authorRepository.save(authorUpdate);
            return authorUpdate;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Record not found with id : " + id);
            return new Author(0L,"Unknown author");
            // throw new ResourceNotFoundException("Record not found with id : " + author.getId().toString());
        }
    }
    @Override
    public Author getById(Long authorId) {
        Optional <Author> authorDb = this.authorRepository.findById(authorId);
        if (authorDb.isPresent()) {
            return authorDb.get();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Record not found with id : " + authorId);
            return new Author(0L,"Unknown author");
            //throw new ResourceNotFoundException("Record not found with id : " + authorId);
        }
    }
    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }
    @Override
    public void delete(Long authorId) {
        Optional <Author> authorDb = this.authorRepository.findById(authorId);

        if (authorDb.isPresent()) {
            this.authorRepository.delete(authorDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + authorId);
        }

    }*/

    public List<Book> getBooks(long authorId){
        List<Book> temp= bookRepository.findAll();
        List<Book> books =new ArrayList<>();
        for (Book b:temp){
            if(b.getAuthorId()==authorId){books.add(b);}
        }
        return books;

    }

    public List<Reader> getReaders(long autorId){
        List<Book> tempB=getBooks(autorId);
        List<Card> tempC=cardRepository.findAll();
        List<Reader> tempR=readerRepository.findAll();
        List<Card> cardList= new ArrayList<>();
        List<Reader> readers= new ArrayList<>();
        for (Book b : tempB){
            for( Card c:tempC){
                if(b.getId()==c.getBookid()){cardList.add(c);}
            }}
        for (Card c: cardList){
            for(Reader r:tempR){
                if(c.getReaderid()==r.getId()){readers.add(r);
            }
        }
        }
        return readers;
    }
}
