package org.example.lcs.common.exceptions;

public class DataBaseOperationException extends RuntimeException {

    public DataBaseOperationException(String message) {
        super(message);
    }

    public DataBaseOperationException(Throwable cause) {
        super(cause);
    }
}
