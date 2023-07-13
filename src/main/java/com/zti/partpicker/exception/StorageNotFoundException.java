package com.zti.partpicker.exception;

public class StorageNotFoundException extends RuntimeException {
    public StorageNotFoundException(Long id) {
        super("Could not find Storage of id: " + id);
    }
}