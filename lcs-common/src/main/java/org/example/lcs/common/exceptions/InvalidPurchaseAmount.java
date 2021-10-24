package org.example.lcs.common.exceptions;

public class InvalidPurchaseAmount extends RuntimeException {
    public InvalidPurchaseAmount(String message) {
        super(message);
    }

    public InvalidPurchaseAmount(Throwable cause) {
        super(cause);
    }
}