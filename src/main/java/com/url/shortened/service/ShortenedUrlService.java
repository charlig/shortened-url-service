package com.url.shortened.service;

import org.springframework.data.crossstore.ChangeSetPersister;

public interface ShortenedUrlService {
    String createShortenedUrl(String originalUrl);
    String getOriginalUrl(String shortenedUrl) throws ChangeSetPersister.NotFoundException;
}
