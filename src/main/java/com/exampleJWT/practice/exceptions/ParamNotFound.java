package com.exampleJWT.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParamNotFound extends RuntimeException{
    public ParamNotFound(String error){
        super(error);
    }
}
