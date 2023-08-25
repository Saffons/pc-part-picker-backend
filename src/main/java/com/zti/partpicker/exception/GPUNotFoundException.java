package com.zti.partpicker.exception;

/**
 * Exception class for case where GPU object is not found
 */
public class GPUNotFoundException extends RuntimeException {
    /**
     * Constructor for the GPUNotFoundException class
     * @param id ID of absent GPU
     */
    public GPUNotFoundException(Long id) {
        super("Could not find GPU of id: " + id);
    }
}