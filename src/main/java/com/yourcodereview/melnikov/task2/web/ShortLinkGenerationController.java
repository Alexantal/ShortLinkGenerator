package com.yourcodereview.melnikov.task2.web;

import com.yourcodereview.melnikov.task2.DTO.OriginalLinkDto;
import com.yourcodereview.melnikov.task2.DTO.ShortLinkDto;
import com.yourcodereview.melnikov.task2.service.ShortLinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ShortLinkGenerationController.GENERATE_URL)
@AllArgsConstructor
@Slf4j
public class ShortLinkGenerationController {

    static final String GENERATE_URL = "/generate";

    private final ShortLinkService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShortLinkDto> generateShortLink(@RequestBody OriginalLinkDto originalLink) {
        String longLink = originalLink.getOriginal();
        log.info("Short link generation request for original link: {}", longLink);
        String newLink = service.findOrGenerateShortLinkByOriginalUrl(longLink);
        log.info("Short link is: {}", newLink);
        return ResponseEntity.ok(new ShortLinkDto(newLink));
    }
}
