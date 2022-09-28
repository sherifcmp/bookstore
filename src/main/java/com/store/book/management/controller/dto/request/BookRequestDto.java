package com.store.book.management.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private float price;
    private byte[] coverImage;
    private AuthorRequestDto author;

    public BookRequestDto(String title, String description, float price, byte[] coverImage) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.coverImage = coverImage;
    }
}
