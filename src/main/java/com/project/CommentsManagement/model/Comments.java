package com.project.CommentsManagement.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COMMENTS")
@NamedQueries({
        @NamedQuery(
                name = "Comments.findByUsername",
                query = "SELECT c FROM Comments c WHERE c.by = :username"
        ),
        @NamedQuery(
                name = "Comments.findByDate",
                query = "SELECT c FROM Comments c WHERE c.dateofcomment = :date"
        )
})
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String by;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private LocalDateTime dateofcomment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateofcomment() {
        return dateofcomment;
    }

    public void setDateofcomment(LocalDateTime dateofcomment) {
        this.dateofcomment = dateofcomment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return Objects.equals(id, comments.id) && Objects.equals(by, comments.by) && Objects.equals(text, comments.text) && Objects.equals(dateofcomment, comments.dateofcomment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, by, text, dateofcomment);
    }
}

