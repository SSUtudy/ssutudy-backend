package com.ssutudy.backend;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.report.LevelResolver;
import com.atlassian.oai.validator.report.ValidationReport;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;

public class ApiTestContext {

    private final TestRestTemplate testRestTemplate;

    public ApiTestContext(int port) {
        final String rootUri = "http://localhost:"+port;

        OpenApiValidationClientHttpRequestInterceptor validationInterceptor = new OpenApiValidationClientHttpRequestInterceptor(
            OpenApiInteractionValidator.createForSpecificationUrl("spec.yaml")
                .withLevelResolver(
                    LevelResolver.create().withLevel("validation.request", ValidationReport.Level.IGNORE).build()
                )
                .build()
        );

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
            .interceptors(Collections.singleton(validationInterceptor))
            .uriTemplateHandler(new DefaultUriBuilderFactory(rootUri))
            .rootUri(rootUri);

        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    public TestRestTemplate getTestRestTemplate() {
        return testRestTemplate;
    }
}
