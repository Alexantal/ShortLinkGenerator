package com.yourcodereview.melnikov.task2.web;

import com.yourcodereview.melnikov.task2.service.ShortLinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = RedirectController.REDIRECT_URL)
@AllArgsConstructor
@Slf4j
public class RedirectController {

    static final String REDIRECT_URL = "/l";

    private final ShortLinkService service;

    @GetMapping("/{shortLink}")
    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    public ResponseEntity<Void> redirect(@PathVariable String shortLink) {
        String incomingShortLink = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        log.info("Redirect is starting. Short link is: {}", incomingShortLink);
        String originalLink = service.findOriginalLinkByShortLink(incomingShortLink);
        return originalLink.equals("")
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(URI.create(originalLink)).build();
    }
}
