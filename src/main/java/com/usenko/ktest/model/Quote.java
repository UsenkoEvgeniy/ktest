package com.usenko.ktest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modifiedDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @OneToMany(mappedBy = "quote", orphanRemoval = true)
    @ToString.Exclude
    private Set<Vote> votes = new HashSet<>();
}
