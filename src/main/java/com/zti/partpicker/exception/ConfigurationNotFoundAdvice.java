package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice class responsible for handling Configuration related exceptions
 */
@ControllerAdvice
class ConfigurationNotFoundAdvice {

    /**
     *
     * @param ex ConfigurationNotFoundException
     * @return message from exception
     */
    @ResponseBody
    @ExceptionHandler(ConfigurationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String configurationNotFoundHandler(ConfigurationNotFoundException ex) {
        return ex.getMessage();
    }
}