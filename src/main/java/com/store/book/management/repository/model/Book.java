package com.store.book.management.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "BOOKS")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title, description;

    private float price;

    @Lob
    private byte[] coverImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(Long id, String title, String description, float price, byte[] coverImage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.coverImage = coverImage;
    }
}