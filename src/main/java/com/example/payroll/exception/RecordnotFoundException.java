package com.example.payroll.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class RecordnotFoundException extends Exception {

    public RecordnotFoundException(String message) {
        
        super(message);
    }
}