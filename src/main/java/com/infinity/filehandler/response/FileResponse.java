package com.infinity.filehandler.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class FileResponse {
    private String message;
    private List<String> outputiles;
}
