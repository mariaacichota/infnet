package com.infnet.company.service;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) { super(message); }
}