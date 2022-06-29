package com.yourcodereview.melnikov.task2.dto;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;

@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkDto {

    private String link;
}
