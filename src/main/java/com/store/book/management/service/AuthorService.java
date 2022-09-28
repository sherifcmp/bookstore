package com.store.book.management.service;

import com.store.book.management.exception.BadRequestException;
import com.store.book.management.exception.NotFoundException;
import com.store.book.management.repository.AuthorRepository;
import com.store.book.management.repository.model.Author;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.store.book.management.exception.ExceptionConstants.*;

@Log4j2
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author author) {
        isAuthorExists(author);
        isAuthorNameEmpty(author);
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            log.debug(AUTHORS_NOT_FOUND);
            throw new NotFoundException(AUTHORS_NOT_FOUND);
        }
        return authors;
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> {
            log.debug(AUTHOR_NOT_FOUND);
            throw new NotFoundException(AUTHOR_NOT_FOUND);
        });
    }

    public Author updateAuthorById(Author author) {
        getAuthorById(author.getId());
        isAuthorNameEmpty(author);
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long authorId) {
        getAuthorById(authorId);
        Author author = getAuthorById(authorId);
        authorRepository.delete(author);
    }

    private void isAuthorNameEmpty(Author author) {
        if (author.getName().isEmpty())
            throw new BadRequestException(REQUIRED_AUTHOR_NAME);
    }

    private void isAuthorExists(Author author){
        if (author.getId() != null && Objects.nonNull(getAuthorById(author.getId())))
            throw new BadRequestException(AUTHOR_EXISTS);
    }

}
