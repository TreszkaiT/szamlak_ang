package com.treszkai.szamlak.exception;

/**
 * Saját hibaüzenet
 *
 *
 *
 */
public class CurrencyNotFoundException  extends RuntimeException {

    public CurrencyNotFoundException(String message) {
        super(message);
    }
}