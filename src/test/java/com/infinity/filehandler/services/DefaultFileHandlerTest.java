package com.infinity.filehandler.services;

import com.infinity.filehandler.utils.FileProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultFileHandlerTest {
    private FileProperties properties = Mockito.mock(FileProperties.class);
    private DefaultFileHandler handler;

    @BeforeAll
    public void setup() {
        Mockito.when(properties.getInputDir()).thenReturn("C:\\temp\\filehandler\\input");
        Mockito.when(properties.getOutputDir()).thenReturn("C:\\temp\\filehandler\\output");
        Mockito.when(properties.getModiferString()).thenReturn("sample text data");

        handler = new DefaultFileHandler(properties);
    }

    @Test
    public void shouldTestSupports() {
        List<String> supports = handler.supports();

        Assertions.assertEquals(supports, Arrays.asList("C:\\", "/"));
    }

    @Test
    public void shouldTestModify() {
        List<String> filePaths = Arrays.asList(properties.getOutputDir() + File.separator + getCurrentDate() + File.separator + "business-profile-data.txt", properties.getOutputDir() + File.separator + getCurrentDate() + File.separator + "customer-retail-data.txt", properties.getOutputDir() + File.separator + getCurrentDate() + File.separator + "premium-customer-leads.txt");
        List<String> expectedOutputPaths = Arrays.asList(properties.getOutputDir() + File.separator + getCurrentDate() + File.separator + "business-profile-data.txt", properties.getOutputDir() + File.separator + getCurrentDate() + File.separator + "customer-retail-data.txt", properties.getOutputDir() + File.separator + getCurrentDate() + File.separator + "premium-customer-leads.txt");

        List<String> outputPaths = handler.modify(filePaths);

        Assertions.assertEquals(expectedOutputPaths, outputPaths);
    }

    private String getCurrentDate() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
