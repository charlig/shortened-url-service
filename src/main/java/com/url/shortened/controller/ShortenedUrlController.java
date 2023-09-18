package com.url.shortened.controller;

import com.url.shortened.config.DomainConfig;
import com.url.shortened.dto.OriginalUrlRecord;
import com.url.shortened.service.ShortenedUrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class ShortenedUrlController {
    private final ShortenedUrlService shortenedUrlService;
    private final DomainConfig domainConfig;

    Logger log = LoggerFactory.getLogger(ShortenedUrlController.class);



    public ShortenedUrlController(ShortenedUrlService shortenedUrlService, DomainConfig domainConfig) {
        this.shortenedUrlService = shortenedUrlService;
        this.domainConfig = domainConfig;
    }

    @PostMapping(value = "/shorten",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createShortenUrl(@Valid @RequestBody OriginalUrlRecord originalUrlRecord){
        String shortenedCode = shortenedUrlService.createShortenedUrl(originalUrlRecord.originalUrl());
        return ResponseEntity.ok(domainConfig.getDomainUrl()+shortenedCode);
    }

    @GetMapping("/{shortenedCode}")
    public void redirectToOriginalUrl(@PathVariable @NotBlank(message = "Shortened code cannot be blank")  String shortenedCode, HttpServletResponse response) throws IOException, ChangeSetPersister.NotFoundException {
        String originalUrl = shortenedUrlService.getOriginalUrl(shortenedCode);
        log.info("Redirecting to original URL: " + originalUrl);
        response.sendRedirect(originalUrl);
    }


}
