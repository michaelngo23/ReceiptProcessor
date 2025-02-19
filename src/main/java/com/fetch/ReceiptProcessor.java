package com.fetch;  // ✅ This should match your folder structure

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReceiptProcessor {
    public static void main(String[] args) {
        SpringApplication.run(ReceiptProcessor.class, args);
    }
}
