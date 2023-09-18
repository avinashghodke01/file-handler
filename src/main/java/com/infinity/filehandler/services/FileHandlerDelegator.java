package com.infinity.filehandler.services;

import com.infinity.filehandler.error.FileHandlerException;
import com.infinity.filehandler.request.FileRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class FileHandlerDelegator {
    private List<FileHandler> handlers;

    public FileHandlerDelegator(List<FileHandler> handlers) {
        this.handlers = handlers;
    }

    public FileHandler delegate(FileRequest request) {
        validate(request.getData());

        String filename = request.getData().get(0);
        return this.handlers
                .stream()
                .filter(h -> StringUtils.startsWithIgnoreCase(filename, h.supports()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("No handler defined"));
    }

    private void validate(List<String> filePaths) {
        if (CollectionUtils.isEmpty(filePaths)) {
            throw new FileHandlerException("validation error: file paths not provided");
        }
    }
}
