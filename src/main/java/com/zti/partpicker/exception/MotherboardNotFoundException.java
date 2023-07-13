package com.zti.partpicker.exception;

public class MotherboardNotFoundException extends RuntimeException {
    public MotherboardNotFoundException(Long id) {
        super("Could not find Motherboard of id: " + id);
    }
}