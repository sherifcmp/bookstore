package com.store.book.management.controller.dto.response;

import com.store.book.management.controller.dto.response.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private float price;
    private byte[] coverImage;
    private AuthorDto author;

    public BookDto(String title, String description, float price, byte[] coverImage) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.coverImage = coverImage;
    }
}
