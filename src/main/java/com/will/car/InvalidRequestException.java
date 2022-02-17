package com.will.car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends IllegalStateException{

    public InvalidRequestException(String message){
        super(message);
    }
}
