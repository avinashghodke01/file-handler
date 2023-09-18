package com.infinity.filehandler.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudFileHandler implements FileHandler {

    @Override
    public String supports() {
        return "gs://";
    }

    @Override
    public List<String> modify(List<String> filePath) {
        throw new RuntimeException("Cloud storage file handler not defined.");
    }
}
