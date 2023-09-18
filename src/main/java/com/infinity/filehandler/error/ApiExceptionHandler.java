package com.infinity.filehandler.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = FileHandlerException.class)
    public ResponseEntity<FileHandlerError> handleFileException(RuntimeException ex, WebRequest request) {
        FileHandlerError err = FileHandlerError.builder()
                .code(500)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(500).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<FileHandlerError> handleRuntime(RuntimeException ex, WebRequest request) {
        FileHandlerError err = FileHandlerError.builder()
                .code(500)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(500).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<FileHandlerError> handleRuntime(Exception ex, WebRequest request) {
        FileHandlerError err = FileHandlerError.builder()
                .code(500)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(500).body(err);
    }
}
