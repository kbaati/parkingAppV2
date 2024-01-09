package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataRetrievalException extends RuntimeException {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataRetrievalException.class);


    public DataRetrievalException(String message) {
        super(message);
    }
    public DataRetrievalException(String message, Exception cause) {

        super(message,cause);
        LOGGER.error("cause: "+cause.getMessage());

        //System.out.println("cause: "+cause.getMessage());

    }
}
