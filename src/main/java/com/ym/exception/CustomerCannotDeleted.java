package com.ym.exception;

public class CustomerCannotDeleted extends RuntimeException {

    public CustomerCannotDeleted(String msg){
        super(msg);
    }
}
