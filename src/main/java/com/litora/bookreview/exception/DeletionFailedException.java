package com.litora.bookreview.exception;

public class DeletionFailedException extends RuntimeException {
    public DeletionFailedException(String message) {
        super(message);
    }
}
