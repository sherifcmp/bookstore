package com.store.book.management.repository;

import com.store.book.management.repository.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("select b from BOOKS b where ( :from IS NULL OR b.price >= :from ) AND " +
            "( :to IS NULL OR b.price <= :to ) AND " +
            "( :title IS NULL OR lower(b.title) LIKE lower(concat('%',:title,'%')) ) AND " +
            "( :description IS NULL OR lower(b.description) LIKE lower(concat('%',:description,'%')) ) " +
            "order by b.id")
    List<Book> findByParams(@Param("from") Float priceFrom, @Param("to") Float priceTo, @Param("title") String title, @Param("description") String description);
}