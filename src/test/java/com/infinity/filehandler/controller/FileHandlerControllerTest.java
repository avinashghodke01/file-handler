package com.infinity.filehandler.controller;

import com.infinity.filehandler.request.FileRequest;
import com.infinity.filehandler.response.FileResponse;
import com.infinity.filehandler.services.FileHandler;
import com.infinity.filehandler.services.FileHandlerDelegator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileHandlerControllerTest {
    private FileHandlerDelegator delegator = Mockito.mock(FileHandlerDelegator.class);
    private FileHandler handler = Mockito.mock(FileHandler.class);
    private FileHandlerController controller;

    @BeforeAll
    public void setup() {
        controller = new FileHandlerController(delegator);
        Mockito.when(delegator.delegate(ArgumentMatchers.any())).thenReturn(handler);
        Mockito.when(handler.modify(ArgumentMatchers.any())).thenReturn(Arrays.asList("C:\\temp\\filehandler\\output\\20230915\\business-profile-data.txt",
                "C:\\temp\\filehandler\\output\\20230915\\customer-retail-data.txt",
                "C:\\temp\\filehandler\\output\\20230915\\premium-customer-leads.txt"));
    }

    @Test
    public void shouldTestModifier() {
        ResponseEntity<FileResponse> actual = controller.modifier(getMockRequest());

        FileResponse response = FileResponse.builder()
                .message("Files modified successfully!")
                .outputiles(Arrays.asList("C:\\temp\\filehandler\\output\\20230915\\business-profile-data.txt",
                        "C:\\temp\\filehandler\\output\\20230915\\customer-retail-data.txt",
                        "C:\\temp\\filehandler\\output\\20230915\\premium-customer-leads.txt"))
                .build();
        ResponseEntity respnseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);

        Assertions.assertEquals(respnseEntity, actual);
    }

    private FileRequest getMockRequest() {
        return FileRequest.builder()
                .data(Arrays.asList("C:\\temp\\filehandler\\input\\business-profile-data.txt",
                        "C:\\temp\\filehandler\\input\\customer-retail-data.txt",
                        "C:\\temp\\filehandler\\input\\premium-customer-leads.txt"))
                .build();
    }
}
