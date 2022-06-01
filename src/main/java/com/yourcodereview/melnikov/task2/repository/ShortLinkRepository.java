package com.yourcodereview.melnikov.task2.repository;

import com.yourcodereview.melnikov.task2.model.LinkHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ShortLinkRepository extends JpaRepository<LinkHolder, Integer> {

    Optional<LinkHolder> findByOriginalLink(String originalLink);

    Optional<LinkHolder> findByShortLink(String shortLink);
}
