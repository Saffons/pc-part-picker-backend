package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice class responsible for handling Storage related exceptions
 */
@ControllerAdvice
class StorageNotFoundAdvice {

    /**
     *
     * @param ex StorageNotFoundException
     * @return message from exception
     */
    @ResponseBody
    @ExceptionHandler(StorageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String storageNotFoundHandler(StorageNotFoundException ex) {
        return ex.getMessage();
    }
}