package ru.ponomarev.MySecondApp.exception;
public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) {
        super(message);
    }
}