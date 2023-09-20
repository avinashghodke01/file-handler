package com.infinity.filehandler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CloudFileHandlerTest {
    private CloudFileHandler handler;

    @BeforeAll
    public void setup() {
        this.handler = new CloudFileHandler();
    }

    @Test
    public void shouldTestSupports() {
        List<String> actual = handler.supports();

        Assertions.assertEquals(Arrays.asList("gs://"), actual);
    }

    @Test
    public void shouldTestModify() {
        Assertions.assertThrows(RuntimeException.class, () -> handler.modify(Collections.emptyList()));
    }
}
