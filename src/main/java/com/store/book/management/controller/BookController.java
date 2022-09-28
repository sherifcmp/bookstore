package com.store.book.management.controller;

import com.store.book.management.controller.dto.response.AuthorDto;
import com.store.book.management.controller.dto.response.BookDto;
import com.store.book.management.repository.model.Book;
import com.store.book.management.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto) {
        Book book = convertFromDto(bookDto);
        return convertToDto(bookService.addBook(book));
    }

    @GetMapping
    public List<BookDto> getAllBooks(@RequestParam(value = "from", required = false) Float priceFrom, @RequestParam(value = "to", required = false) Float priceTo, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "description", required = false) String description) {
        return bookService.getAllBooks(priceFrom, priceTo, title, description).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{book-id}")
    public BookDto getBookById(@PathVariable("book-id") Long bookId) {
        return convertToDto(bookService.getBookById(bookId));
    }

    @PutMapping("/{book-id}")
    public BookDto updateBookById(@RequestBody BookDto bookDto) {
        return convertToDto(bookService.updateBookById(convertFromDto(bookDto)));
    }

    @DeleteMapping("/{book-id}")
    public void deleteBookById(@PathVariable("book-id") Long bookId) {
        bookService.deleteBookById(bookId);
    }

    private BookDto convertToDto(Book book) {
        AuthorDto authorDto = null;
        if (Objects.nonNull(book.getAuthor()) && Objects.nonNull(book.getId()))
            authorDto = new AuthorDto(
                    book.getAuthor().getId(),
                    (Objects.nonNull(book.getAuthor())) ? book.getAuthor().getName() : "");

        return new BookDto(book.getId(), book.getTitle(), book.getDescription(), book.getPrice(),
                book.getCoverImage(),
                authorDto
        );
    }

    private Book convertFromDto(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getDescription(), bookDto.getPrice(), bookDto.getCoverImage());
    }

}
