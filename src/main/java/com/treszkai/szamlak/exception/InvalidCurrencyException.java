package com.treszkai.szamlak.exception;

import java.util.List;

/**
 * a ContorllerAdvice Validation hibáit kapja el a Controller-en keresztül
 */
public class InvalidCurrencyException extends RuntimeException {

    private List<String> message;

    public InvalidCurrencyException(String message, List<String> messages) {           // egy hibaüzenetet és egy messages listát adunk meg, hogy mi volt a hiba
        super(message);
        this.message = messages;
    }

    // hogy a movieContorllerAdvice-ben lekérhessem az exception.getmessages()-t
//    @Override
//    public List<String> getMessage() {
//        return message;
//    }
}