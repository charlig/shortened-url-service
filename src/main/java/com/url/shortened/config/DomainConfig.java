package com.url.shortened.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DomainConfig {
    @Value("${domain.url}")
    private String domainUrl;
}
