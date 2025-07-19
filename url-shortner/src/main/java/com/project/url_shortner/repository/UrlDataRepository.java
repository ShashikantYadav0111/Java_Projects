package com.project.url_shortner.repository;

import com.project.url_shortner.model.UrlData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlDataRepository extends MongoRepository<UrlData, UUID> {

    Optional<UrlData> findByShortUrl(String shortUrl);
}
