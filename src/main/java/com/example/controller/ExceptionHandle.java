package com.example.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {
    //统一处理异常
    @ExceptionHandler(value = Exception.class)
    public void handleException(Exception e){
        e.printStackTrace();
    }
}
