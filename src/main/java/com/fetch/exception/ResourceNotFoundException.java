package com.fetch.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final List<String> errors;

    public ResourceNotFoundException(List<String> errors) {
        this.errors = errors;
    }
}