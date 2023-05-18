package com.zti.partpicker.exception;

public class CPUNotFoundException extends RuntimeException {
    public CPUNotFoundException(Long id) {
        super("Could not find CPU of id: " + id);
    }
}