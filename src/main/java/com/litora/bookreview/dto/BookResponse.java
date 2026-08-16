package com.litora.bookreview.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        String genre,
        Integer publishedYear) {
}
