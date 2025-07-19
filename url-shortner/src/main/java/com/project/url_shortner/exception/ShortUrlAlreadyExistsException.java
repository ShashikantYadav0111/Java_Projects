package com.project.url_shortner.exception;

public class ShortUrlAlreadyExistsException extends RuntimeException {
    public ShortUrlAlreadyExistsException(String message) {
        super(message);
    }
}
