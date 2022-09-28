package com.store.book.management.service;

import com.store.book.management.exception.BadRequestException;
import com.store.book.management.exception.NotFoundException;
import com.store.book.management.repository.BookRepository;
import com.store.book.management.repository.model.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.store.book.management.exception.ExceptionConstants.*;

@Log4j2
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        isBookExists(book);
        isBookTitleEmpty(book);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(Float priceFrom, Float priceTo, String title, String description) {
        List<Book> books = bookRepository.findByParams(priceFrom, priceTo, title, description);
        if (books.isEmpty()) {
            log.debug(BOOKS_NOT_FOUND);
            throw new NotFoundException(BOOKS_NOT_FOUND);
        }
        return books;
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> {
            log.debug(BOOK_NOT_FOUND);
            throw new NotFoundException(BOOK_NOT_FOUND);
        });
    }

    public Book updateBookById(Book book) {
        getBookById(book.getId());
        isBookTitleEmpty(book);
        return bookRepository.save(book);
    }

    public void deleteBookById(Long bookId) {
        getBookById(bookId);
        Book book = getBookById(bookId);
        bookRepository.delete(book);
    }

    private void isBookTitleEmpty(Book book) {
        String title = book.getTitle();
        if (Objects.isNull(title) || title.isEmpty())
            throw new BadRequestException(REQUIRED_BOOK_TITLE);
    }

    private void isBookExists(Book book) {
        if (book.getId() != null && Objects.nonNull(getBookById(book.getId())))
            throw new BadRequestException(BOOK_EXISTS);
    }

}
