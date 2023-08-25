package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice class responsible for handling Motherboard related exceptions
 */
@ControllerAdvice
class MotherboardNotFoundAdvice {

    /**
     *
     * @param ex MotherboardNotFoundException
     * @return message from exception
     */
    @ResponseBody
    @ExceptionHandler(MotherboardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String motherboardNotFoundHandler(MotherboardNotFoundException ex) {
        return ex.getMessage();
    }
}