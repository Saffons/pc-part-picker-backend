package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ConfigurationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ConfigurationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String configurationNotFoundHandler(ConfigurationNotFoundException ex) {
        return ex.getMessage();
    }
}