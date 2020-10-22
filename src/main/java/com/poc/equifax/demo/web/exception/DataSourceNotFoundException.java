package com.poc.equifax.demo.web.exception;

public class DataSourceNotFoundException extends RuntimeException {
    public DataSourceNotFoundException(String message) {
        super(message);
    }
}
