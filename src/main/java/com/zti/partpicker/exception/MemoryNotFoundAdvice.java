package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice class responsible for handling Memory related exceptions
 */
@ControllerAdvice
class MemoryNotFoundAdvice {

    /**
     *
     * @param ex MemoryNotFoundException
     * @return message from exception
     */
    @ResponseBody
    @ExceptionHandler(MemoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String memoryNotFoundHandler(MemoryNotFoundException ex) {
        return ex.getMessage();
    }
}