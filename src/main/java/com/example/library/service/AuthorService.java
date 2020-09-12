package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthorService implements CRUDService<Author>{

    @Autowired
    AuthorRepository authorRepository;

    @Override
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

    }
}
