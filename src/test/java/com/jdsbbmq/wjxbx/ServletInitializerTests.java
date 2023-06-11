package com.jdsbbmq.wjxbx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServletInitializerTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void configureTest() {
        // just for test coverage
        ServletInitializer initializer = new ServletInitializer();
        SpringApplicationBuilder builderMock = mock(SpringApplicationBuilder.class);
        when(builderMock.sources(any(Class.class))).thenReturn(builderMock);

        initializer.configure(builderMock);

        verify(builderMock, times(1)).sources(any(Class.class));
    }
}
