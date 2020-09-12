package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BookStatus;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookService implements CRUDService<Book> {

    @Autowired
    BookRepository bookRepository;


    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book, Long id){
        Optional<Book> bookDb = this.bookRepository.findById(id);

        if (bookDb.isPresent()) {
            Book bookUpdate = bookDb.get();
            bookUpdate.setCaption(book.getCaption());
            bookRepository.save(bookUpdate);
            return bookUpdate;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Record not found with id : " + id);
            return new Book(0L,"Unknown book",0L, BookStatus.BORROWED);
            // throw new ResourceNotFoundException("Record not found with id : " + book.getId().toString());
        }
    }

    @Override
    public Book getById(Long bookId) {
        Optional <Book> bookDb = this.bookRepository.findById(bookId);
        if (bookDb.isPresent()) {
            return bookDb.get();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Record not found with id : " + bookId);
            return new Book(0L,"Unknown book",0L, BookStatus.BORROWED);
            //throw new ResourceNotFoundException("Record not found with id : " + bookId);
        }
    }
    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public void delete(Long bookId) {
        Optional <Book> bookDb = this.bookRepository.findById(bookId);

        if (bookDb.isPresent()) {
            this.bookRepository.delete(bookDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + bookId);
        }

    }
}
