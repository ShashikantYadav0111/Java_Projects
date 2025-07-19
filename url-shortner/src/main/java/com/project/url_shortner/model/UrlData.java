package com.project.url_shortner.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlData {
    @Id
    private UUID uuid;
    private String longUrl;
    @Indexed(unique = true)
    private String shortUrl;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime expiration;
}
