package com.yourcodereview.melnikov.task2.service;

import com.yourcodereview.melnikov.task2.model.LinkHolder;
import com.yourcodereview.melnikov.task2.repository.ShortLinkRepository;
import com.yourcodereview.melnikov.task2.util.LinkUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShortLinkService {

    private final ShortLinkRepository repository;

    public String findOrGenerateShortLinkByOriginalUrl(String originalUrl) {
        LinkHolder checkingHolder = repository.findByOriginalLink(originalUrl).orElse(new LinkHolder());
        return checkingHolder.isNew() ? generateShortLinkByOriginalUrl(originalUrl) : checkingHolder.generateShortLink();
    }

    private String generateShortLinkByOriginalUrl(String originalUrl) {
        LinkHolder newHolder = repository.save(new LinkHolder(originalUrl));
        return newHolder.generateShortLink();
    }

    public String findOriginalLinkByShortLink(String shortLink) {
        long id = LinkUtil.decodeShortLink(shortLink);
        LinkHolder result = repository.findById(id).orElse(null);
        return result != null ? result.getOriginalLink() : "";
    }
}
