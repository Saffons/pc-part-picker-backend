package com.zti.partpicker.exception;

/**
 * Exception class for case where Configuration object is not found
 */
public class ConfigurationNotFoundException extends RuntimeException {
    /**
     * Constructor for the ConfigurationNotFoundException class
     * @param id ID of absent Configuration
     */
    public ConfigurationNotFoundException(Long id) {
        super("Could not find Configuration of id: " + id);
    }
}