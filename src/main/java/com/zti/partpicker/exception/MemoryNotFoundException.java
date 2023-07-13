package com.zti.partpicker.exception;

public class MemoryNotFoundException extends RuntimeException {
    public MemoryNotFoundException(Long id) {
        super("Could not find Memory of id: " + id);
    }
}