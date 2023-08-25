package com.zti.partpicker.exception;

/**
 * Exception class for case where Motherboard object is not found
 */
public class MotherboardNotFoundException extends RuntimeException {
    /**
     * Constructor for the MotherboardNotFoundException class
     * @param id ID of absent Motherboard
     */
    public MotherboardNotFoundException(Long id) {
        super("Could not find Motherboard of id: " + id);
    }
}