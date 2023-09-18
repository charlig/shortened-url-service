package com.url.shortened.model;

import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("ShortenedUrl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortenedUrl {
    @Id
    private String id;
    @com.mongodb.lang.NonNull
    @Indexed(unique = true)
    private String originalUrl;
    @NonNull
    @Indexed(unique = true)
    private String shortenCode;
}
