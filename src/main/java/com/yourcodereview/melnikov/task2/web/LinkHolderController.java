package com.yourcodereview.melnikov.task2.web;

import com.yourcodereview.melnikov.task2.model.LinkHolder;
import com.yourcodereview.melnikov.task2.repository.ShortLinkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/links")
@AllArgsConstructor
@Slf4j
public class LinkHolderController {

    private final ShortLinkRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinkHolder> getAll() {
        log.info("get all links");
        return repository.findAll();
    }
}
