package com.yourcodereview.melnikov.task2.web;

import com.yourcodereview.melnikov.task2.dto.OriginalLinkDto;
import com.yourcodereview.melnikov.task2.dto.ShortLinkDto;
import com.yourcodereview.melnikov.task2.service.ShortLinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ShortLinkGenerationController.GENERATE_URL)
@AllArgsConstructor
public class ShortLinkGenerationController {

    static final String GENERATE_URL = "/generate";

    private final ShortLinkService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShortLinkDto> generateShortLink(@RequestBody OriginalLinkDto originalLink) {
        String longLink = originalLink.getOriginal();
        String newLink = service.findOrGenerateShortLinkByOriginalUrl(longLink);
        return ResponseEntity.ok(new ShortLinkDto(newLink));
    }
}
