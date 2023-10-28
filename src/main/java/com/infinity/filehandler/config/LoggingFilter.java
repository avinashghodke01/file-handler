package com.infinity.filehandler.config;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        String requestBody = getValueAsString(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        LOG.info("#processing request: METHOD:{}, URI:{}, PAYLOAD:{}", request.getMethod(), request.getRequestURI(), requestBody);
        long startTime = System.currentTimeMillis();

        filterChain.doFilter(requestWrapper, responseWrapper);

        long timeTaken = System.currentTimeMillis() - startTime;
        String responseBody = getValueAsString(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
        LOG.info("total time taken:{}", timeTaken);
        LOG.info("Response details: CODE:{}, RESPONSE BODY:{}", response.getStatus(), responseBody);
        responseWrapper.copyBodyToResponse();
    }

    @SneakyThrows
    private String getValueAsString(byte[] content, String charEncoding) {
        return new String(content, 0, content.length, charEncoding);
    }
}
