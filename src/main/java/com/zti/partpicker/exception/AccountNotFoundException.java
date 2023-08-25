package com.zti.partpicker.exception;

/**
 * Exception class for case where Account object is not found
 */
public class AccountNotFoundException extends RuntimeException {
    /**
     * Constructor for the AccountNotFoundException class
     * @param id ID of absent Account
     */
    public AccountNotFoundException(Long id) {
        super("Could not find account of id: " + id);
    }
}