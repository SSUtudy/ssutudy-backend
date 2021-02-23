package com.ssutudy.backend;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ApiIntegrationTest {
    protected ApiTestContext apiTestContext;

    @Autowired
    public ApiIntegrationTest(ServletWebServerApplicationContext servletWebServerApplicationContext) {
        int port = servletWebServerApplicationContext.getWebServer().getPort();
        apiTestContext = new ApiTestContext(port);
    }
}
