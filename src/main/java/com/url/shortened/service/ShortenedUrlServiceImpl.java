package com.url.shortened.service;

import com.google.common.hash.Hashing;
import com.url.shortened.model.ShortenedUrl;
import com.url.shortened.repository.ShortenedUrlRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
public class ShortenedUrlServiceImpl implements ShortenedUrlService{

    private final ShortenedUrlRepository shortenedUrlRepository;
    private final RedisTemplate<String,String> redisTemplate;

    public ShortenedUrlServiceImpl(ShortenedUrlRepository shortenedUrlRepository, RedisTemplate<String, String> redisTemplate) {
        this.shortenedUrlRepository = shortenedUrlRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String createShortenedUrl(String originalUrl) {
        String shortenedCode = Hashing.murmur3_32_fixed().hashString(originalUrl, StandardCharsets.UTF_8).toString();;
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setOriginalUrl(originalUrl);
        shortenedUrl.setShortenCode(shortenedCode);
        shortenedUrlRepository.save(shortenedUrl);
        redisTemplate.opsForValue().set(shortenedCode, originalUrl);
        redisTemplate.expire(shortenedCode, 30, TimeUnit.DAYS);  // Set TTL to 30 days

        return shortenedCode;
    }

    @Override
    public String getOriginalUrl(String shortenedUrl) throws ChangeSetPersister.NotFoundException {
        String originalUrl = redisTemplate.opsForValue().get(shortenedUrl);
        if (originalUrl == null) {
            originalUrl = shortenedUrlRepository.findByShortenCode(shortenedUrl)
                    .map(ShortenedUrl::getOriginalUrl)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            redisTemplate.opsForValue().set(shortenedUrl, originalUrl);
        }
        return originalUrl;
    }
}
