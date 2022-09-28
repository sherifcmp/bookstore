package com.store.book.management.controller;

import com.store.book.management.controller.dto.response.AuthorDto;
import com.store.book.management.repository.model.Author;
import com.store.book.management.repository.model.Book;
import com.store.book.management.service.AuthorService;
import com.store.book.management.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping
    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
        Author author = convertFromDto(authorDto);
        return convertToDto(authorService.addAuthor(author));
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{author-id}")
    public AuthorDto getAuthorById(@PathVariable("author-id") Long authorId) {
        return convertToDto(authorService.getAuthorById(authorId));
    }

    @PutMapping
    public AuthorDto updateAuthorById(@RequestBody AuthorDto authorDto) {
        Author author = convertFromDto(authorDto);
        return convertToDto(authorService.updateAuthorById(author));
    }


    @PutMapping("/{author-id}/books/{book-id}")
    public void assignBookToAuthor(@PathVariable("author-id") Long authorId, @PathVariable("book-id") Long bookId) {
        Book book = bookService.getBookById(bookId);
        book.setAuthor(authorService.getAuthorById(authorId));
        bookService.updateBookById(book);
    }

    @DeleteMapping("/{author-id}")
    public void deleteAuthorById(@PathVariable("author-id") Long authorId) {
        authorService.deleteAuthorById(authorId);
    }

    private AuthorDto convertToDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    private Author convertFromDto(AuthorDto authorDto) {
        return new Author(authorDto.getName());
    }
}
