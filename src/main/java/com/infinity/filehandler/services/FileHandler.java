package com.infinity.filehandler.services;

import java.util.List;

public interface FileHandler {
    String supports();

    List<String> modify(List<String> filePath);
}
