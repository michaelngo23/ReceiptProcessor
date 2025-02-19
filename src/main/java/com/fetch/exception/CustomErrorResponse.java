package com.fetch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomErrorResponse {
    private final int status;
    private final String error;
    private final String message;
}