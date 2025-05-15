package com.grotor.snipetochnya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "search")
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Search extends AbstractEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private Account author;

    @Column(name = "title_entry", nullable = false)
    private String titleEntry;

    @ManyToMany
    @JoinTable(
        name = "search_tech",
        joinColumns = @JoinColumn(name = "search_id"),
        inverseJoinColumns = @JoinColumn(name = "tech_id")
    )
    private List<Tech> techs;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "search_tag",
        joinColumns = @JoinColumn(name = "search_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
