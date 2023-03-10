package com.lp.springframework.beans.factory.exception;

/**
 * @author liupeng1
 */
public class BeansException extends Exception{

    public BeansException() {
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeansException(String message) {
        super(message);
    }
}
