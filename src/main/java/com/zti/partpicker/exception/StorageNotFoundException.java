package com.zti.partpicker.exception;

/**
 * Exception class for case where Storage object is not found
 */
public class StorageNotFoundException extends RuntimeException {
    /**
     * Constructor for the StorageNotFoundException class
     * @param id ID of absent Storage
     */
    public StorageNotFoundException(Long id) {
        super("Could not find Storage of id: " + id);
    }
}