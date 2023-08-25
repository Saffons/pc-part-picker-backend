package com.zti.partpicker.exception;

/**
 * Exception class for case where CPU object is not found
 */
public class CPUNotFoundException extends RuntimeException {
    /**
     * Constructor for the CPUNotFoundException class
     * @param id ID of absent CPU
     */
    public CPUNotFoundException(Long id) {
        super("Could not find CPU of id: " + id);
    }
}