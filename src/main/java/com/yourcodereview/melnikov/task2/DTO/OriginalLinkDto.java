package com.yourcodereview.melnikov.task2.DTO;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;

@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OriginalLinkDto {

    private String original;
}
