package com.yourcodereview.melnikov.task2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class LinkHolder extends BaseEntity {

    @Column
    @NotNull
    private String shortLink;

    @Column
    @NotNull
    private String originalLink;
}
