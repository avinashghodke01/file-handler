package com.infinity.filehandler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CloudFileHandlerTest {
    private CloudFileHandler handler;

    @BeforeAll
    public void setup() {
        this.handler = new CloudFileHandler();
    }

    @Test
    public void shouldTestSupports() {
        String actual = handler.supports();

        Assertions.assertEquals("gs://", actual);
    }

    @Test
    public void shouldTestModify() {
        Assertions.assertThrows(RuntimeException.class, () -> handler.modify(Collections.emptyList()));
    }
}
