package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice class responsible for handling CPU related exceptions
 */
@ControllerAdvice
class CPUNotFoundAdvice {

    /**
     *
     * @param ex CPUNotFoundException
     * @return message from exception
     */
    @ResponseBody
    @ExceptionHandler(CPUNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cpuNotFoundHandler(CPUNotFoundException ex) {
        return ex.getMessage();
    }
}