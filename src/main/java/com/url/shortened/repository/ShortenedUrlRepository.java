package com.url.shortened.repository;

import com.url.shortened.model.ShortenedUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenedUrlRepository extends MongoRepository<ShortenedUrl,String> {
    Optional<ShortenedUrl> findByShortenCode(String shortenedCode);
}
