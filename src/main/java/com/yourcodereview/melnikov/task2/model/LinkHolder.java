package com.yourcodereview.melnikov.task2.model;

import com.yourcodereview.melnikov.task2.util.LinkUtil;
import lombok.*;
import org.hashids.Hashids;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class LinkHolder extends BaseEntity {

    @Column(unique = true)
    @Size(min = 1, max = 2048)
    @NotNull
    private String originalLink = "default name";

    @Column(columnDefinition = "timestamp default now()")
    @NotNull
    private Date created = new Date();

    @Column
    private Date expiry;

    @Column(columnDefinition = "bigint default 0")
    @Range(min = 0)
    @NotNull
    private Long count = 0L;

    public LinkHolder(String originalLink) {
        this.originalLink = originalLink;
        this.created = new Date();
        this. expiry = null;
    }

    public String generateShortLink() {
        return id != null ? LinkUtil.encodeLink(id) : null;
    }
}
