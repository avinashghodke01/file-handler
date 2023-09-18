package com.infinity.filehandler.error;

public class FileHandlerException extends RuntimeException {
    public FileHandlerException(String message, Throwable err) {
        super(message, err);
    }

    public FileHandlerException(String message) {
        super(message);
    }
}
