package com.infinity.filehandler.controller;

import com.infinity.filehandler.request.FileRequest;
import com.infinity.filehandler.response.FileResponse;
import com.infinity.filehandler.services.FileHandler;
import com.infinity.filehandler.services.FileHandlerDelegator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("filehandler")
public class FileHandlerController {
    private FileHandlerDelegator delegator;

    public FileHandlerController(FileHandlerDelegator delegator) {
        this.delegator = delegator;
    }

    @PostMapping("modify")
    public ResponseEntity<FileResponse> modifier(@RequestBody FileRequest request) {
        FileHandler handle = delegator.delegate(request);
        List<String> modifiedFiles = handle.modify(request.getData());

        FileResponse response = FileResponse.builder()
                .message("Files modified successfully!")
                .outputiles(modifiedFiles)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
