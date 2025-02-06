package com.webresources.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ResourceReview {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 5000)
    private String reviewText;  // Отзыв о ресурсе
    private String reviewDate;   // Дата отзыва

    @ManyToOne(fetch = FetchType.LAZY)
    private User reviewer;      // Связь с пользователем, который оставил отзыв

    @ManyToOne(fetch = FetchType.LAZY)
    private Resource resource;  // Связь с интернет-ресурсом, который оценили

    private Integer rating;     // Оценка ресурса, например, от 1 до 5

    public ResourceReview(String reviewText, String reviewDate, User reviewer, Resource resource, Integer rating) {
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.reviewer = reviewer;
        this.resource = resource;
        this.rating = rating;
    }
}