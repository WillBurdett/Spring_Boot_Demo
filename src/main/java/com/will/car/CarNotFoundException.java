package com.will.car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Here we are creating our own exception extending Runtime exception
@ResponseStatus(value = HttpStatus.NOT_FOUND) // here we are overwriting the default status code of 500, to 404
public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message){
        super(message);
    }
}
