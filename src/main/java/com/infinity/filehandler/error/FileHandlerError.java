package com.infinity.filehandler.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileHandlerError {
    private int code;
    private String message;
}
