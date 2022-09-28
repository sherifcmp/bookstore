package com.store.book.management.service;

import com.store.book.management.exception.BadRequestException;
import com.store.book.management.exception.NotFoundException;
import com.store.book.management.repository.BookRepository;
import com.store.book.management.repository.model.Book;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.store.book.management.exception.ExceptionConstants.BOOK_NOT_FOUND;
import static com.store.book.management.exception.ExceptionConstants.REQUIRED_BOOK_TITLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @InjectMocks
    @Spy
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Test
    void getAllBooksByParams_returnBookList() {
        float priceFrom = 43f;
        float priceTo = 56f;

        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1L, "The Twelve Dancing Princesses",
                "a German fairy tale collected by the Brothers Grimm",
                37.5f, null));

        when(bookRepository.findByParams(priceFrom, priceTo, "Twelve", "collected")).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.getAllBooks(priceFrom, priceTo, "Twelve", "collected");

        assertEquals(expectedBooks.size(), actualBooks.size());
        assertEquals(expectedBooks.get(0), actualBooks.get(0));
    }

    @Test
    void deleteBook_IdNotExist_ThrowNotFoundException() {
        when(bookRepository.findById(1L)).thenReturn(null);
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> bookService.deleteBookById(1L),
                BOOK_NOT_FOUND
        );
        assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND));
    }

    @Test
    void addBook_EmptyTitle_ThrowBadRequestException() {
        Book bookToInsert = new Book(null, null, "desc", 30f, null);
        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> bookService.addBook(bookToInsert),
                REQUIRED_BOOK_TITLE
        );
        assertTrue(exception.getMessage().contains(REQUIRED_BOOK_TITLE));
    }
}
