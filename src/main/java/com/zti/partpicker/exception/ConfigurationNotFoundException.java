package com.zti.partpicker.exception;

public class ConfigurationNotFoundException extends RuntimeException {
    public ConfigurationNotFoundException(Long id) {
        super("Could not find Configuration of id: " + id);
    }
}