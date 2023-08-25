package com.zti.partpicker.exception;

/**
 * Exception class for case where Memory object is not found
 */
public class MemoryNotFoundException extends RuntimeException {
    /**
     * Constructor for the MemoryNotFoundException class
     * @param id ID of absent Memory
     */
    public MemoryNotFoundException(Long id) {
        super("Could not find Memory of id: " + id);
    }
}