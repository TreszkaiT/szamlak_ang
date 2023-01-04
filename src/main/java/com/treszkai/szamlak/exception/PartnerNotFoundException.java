package com.treszkai.szamlak.exception;

/**
 * Saját hibaüzenet
 *
 *
 *
 */
public class PartnerNotFoundException  extends RuntimeException {

    public PartnerNotFoundException(String message) {
        super(message);
    }
}
