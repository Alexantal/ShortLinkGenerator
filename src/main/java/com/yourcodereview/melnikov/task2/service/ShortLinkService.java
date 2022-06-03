package com.yourcodereview.melnikov.task2.service;

import com.yourcodereview.melnikov.task2.model.LinkHolder;
import com.yourcodereview.melnikov.task2.repository.ShortLinkRepository;
import lombok.AllArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ShortLinkService {

    private final ShortLinkRepository repository;

    @Transactional
    public String findOrGenerateShortLinkByOriginalUrl(String originalUrl) {
        LinkHolder checkingHolder = repository.findByOriginalLink(originalUrl).orElse(new LinkHolder());
        return checkingHolder.isNew() ? generateShortLinkByOriginalUrl(originalUrl) : checkingHolder.getShortLink();
    }

    public String findOriginalLinkByShortLink(String shortLink) {
        LinkHolder checkingHolder = repository.findByShortLink(shortLink).orElse(new LinkHolder());
        return checkingHolder.isNew() ? "" : checkingHolder.getOriginalLink();
    }

    private String generateShortLinkByOriginalUrl(String originalUrl) {
        int nextLinkHolderPK = repository.findMaxId().orElse(0) + 1;
        int minHashLength = 8;
        Hashids hashids = new Hashids("unique string for base", minHashLength);
        String newShortLink = "/l/" + hashids.encode(nextLinkHolderPK);
        repository.save(new LinkHolder(newShortLink, originalUrl));
        return newShortLink;
    }
}
