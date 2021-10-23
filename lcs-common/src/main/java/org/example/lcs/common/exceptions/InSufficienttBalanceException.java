package org.example.lcs.common.exceptions;

public class InSufficienttBalanceException extends RuntimeException {
    public InSufficienttBalanceException(String message) {
        super(message);
    }

    public InSufficienttBalanceException(Throwable cause) {
        super(cause);
    }
}
