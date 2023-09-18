package com.infinity.filehandler.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileProperties {
    @Value("${filehandler.input.dir}")
    public String inputDir;

    @Value("${filehandler.output.dir}")
    public String outputDir;

    @Value("${filehandler.line.appender}")
    public String modiferString;
}
