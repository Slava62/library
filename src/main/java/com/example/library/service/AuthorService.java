package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author, Long id){
        Optional<Author> authorDb = this.authorRepository.findById(id);

        if (authorDb.isPresent()) {
            Author authorUpdate = authorDb.get();
           // authorUpdate.setId(author.getId());
            authorUpdate.setName(author.getName());
            authorRepository.save(authorUpdate);
            return authorUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + author.getId().toString());
        }
    }

    public Author getAuthorById(Long authorId) {
        Optional <Author> authorDb = this.authorRepository.findById(authorId);
        if (authorDb.isPresent()) {
            return authorDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + authorId);
        }
    }
    public List<Author> getAllReaders() {
        return this.authorRepository.findAll();
    }

    public void deleteReader(Long authorId) {
        Optional <Author> productDb = this.authorRepository.findById(authorId);

        if (productDb.isPresent()) {
            this.authorRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + authorId);
        }

    }
}
