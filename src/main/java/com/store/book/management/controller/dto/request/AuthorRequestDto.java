package com.store.book.management.controller.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorRequestDto implements Serializable {
    private Long id;
    private String name;

    public AuthorRequestDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
