package com.store.book.management.controller.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ErrorResponse {
    private String error;
}