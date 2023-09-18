package com.infinity.filehandler.services;

import com.infinity.filehandler.error.FileHandlerException;
import com.infinity.filehandler.utils.CommonHelper;
import com.infinity.filehandler.utils.FileProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultFileHandler implements FileHandler {
    private Logger LOG = LoggerFactory.getLogger(DefaultFileHandler.class);
    private FileProperties properties;

    public DefaultFileHandler(FileProperties properties) {
        this.properties = properties;
    }

    @Override
    public String supports() {
        return "C:\\";
    }

    @Override
    public List<String> modify(List<String> filePaths) {
        LOG.info("default file handler processing");
        List<String> modifiedFiles = null;
        try {
            modifiedFiles =
                    filePaths.parallelStream()
                            .map(f -> this.modifyFile(new File(f)))
                            .collect(Collectors.toList());
            LOG.info("modifiedFiles:[{}]", modifiedFiles);
        } catch (Exception e) {
            LOG.error("Error occured while processing files:", e);
            throw new RuntimeException("Something went wrong while processing files");
        }
        LOG.info("file processing completed successfully");
        return modifiedFiles;
    }

    @SneakyThrows
    private String modifyFile(File filePath) {
        String outputDir = properties.getOutputDir() + File.separator + CommonHelper.getCurrentDate();
        createDir(outputDir);
        String outputFileName = outputDir + File.separator + filePath.getName();
        FileWriter writer = new FileWriter(outputFileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        LineIterator line = FileUtils.lineIterator(filePath, "UTF-8");
        try {
            while (line.hasNext()) {
                String data = line.nextLine();
                System.out.println(data);
                String modifiedData = data + properties.getModiferString() + System.lineSeparator();

                bufferedWriter.write(modifiedData);
            }
        } finally {
            LineIterator.closeQuietly(line);
            bufferedWriter.close();
        }
        return outputFileName;
    }

    @SneakyThrows
    private void createDir(String outputDir) {
        File file = new File(outputDir);
        if (!file.exists()) {
            Files.createDirectories(Paths.get(outputDir));
        }
    }
}
