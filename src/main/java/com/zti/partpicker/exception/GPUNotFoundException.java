package com.zti.partpicker.exception;

public class GPUNotFoundException extends RuntimeException {
    public GPUNotFoundException(Long id) {
        super("Could not find GPU of id: " + id);
    }
}