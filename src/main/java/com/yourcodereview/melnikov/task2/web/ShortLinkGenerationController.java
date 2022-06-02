package com.yourcodereview.melnikov.task2.web;

import com.yourcodereview.melnikov.task2.DTO.OriginalLinkDto;
import com.yourcodereview.melnikov.task2.DTO.ShortLinkDto;
import com.yourcodereview.melnikov.task2.model.LinkHolder;
import com.yourcodereview.melnikov.task2.repository.ShortLinkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ShortLinkGenerationController.GENERATE_URL)
@AllArgsConstructor
@Slf4j
public class ShortLinkGenerationController {

    static final String GENERATE_URL = "/generate";

    private final ShortLinkRepository repository;

    @RequestMapping(name = GENERATE_URL)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShortLinkDto> generateShortLink(@RequestBody OriginalLinkDto originalLink) {
        String checkLink = originalLink.getOriginal();
        log.info("Short link generation request for original link: {}", checkLink);

        LinkHolder checkLinkHolder = repository.findByOriginalLink(checkLink).orElse(new LinkHolder());

        if (checkLinkHolder.isNew()) {
            log.info("A correspond short link doesn't exist in the link storage. Let's get new one...");
            //plug value for newShortLink
            String newShortLink = "/l/shortLink";
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(newShortLink).build().toUri();
            log.info("New short link is: {}", newShortLink);
            return ResponseEntity.created(uriOfNewResource).body(new ShortLinkDto(newShortLink));
        } else {
            String existedLink = checkLinkHolder.getShortLink();
            log.info("The short link already exists: {}", existedLink);
            return ResponseEntity.ok(new ShortLinkDto(existedLink));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinkHolder> getAll() {
        log.info("getAll");
        return repository.findAll();
    }
}
