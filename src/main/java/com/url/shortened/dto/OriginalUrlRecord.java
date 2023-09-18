package com.url.shortened.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record OriginalUrlRecord(
        @NotBlank(message = "URL cannot be blank")
        @URL(message = "Invalid URL format")
        String originalUrl
) {
}
