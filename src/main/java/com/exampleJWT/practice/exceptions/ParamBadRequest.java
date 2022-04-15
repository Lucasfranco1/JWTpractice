package com.exampleJWT.practice.exceptions;

public class ParamBadRequest extends RuntimeException{

    public ParamBadRequest(String message){
        super(message);
    }
}
