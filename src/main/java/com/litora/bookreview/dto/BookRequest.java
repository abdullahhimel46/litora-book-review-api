package com.litora.bookreview.dto;

public record BookRequest(
        String title,
        String author,
        String genre,
        Integer publishedYear) {
}
