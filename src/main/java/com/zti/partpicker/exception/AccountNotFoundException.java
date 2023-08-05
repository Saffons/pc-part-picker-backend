package com.zti.partpicker.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Could not find account of id: " + id);
    }
}