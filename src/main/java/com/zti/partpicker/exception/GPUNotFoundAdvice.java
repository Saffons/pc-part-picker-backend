package com.zti.partpicker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GPUNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GPUNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gpuNotFoundHandler(GPUNotFoundException ex) {
        return ex.getMessage();
    }
}