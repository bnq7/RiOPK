package com.webresources.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class EvaluationDetails {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String imageUrl;
    private String title;
    private String description;
    private String reviewerName;
    private String reviewId;

    public EvaluationDetails() {
        imageUrl = "default.jpg";
        title = "Название ресурса";
        description = "Описание ресурса";
        reviewerName = "Неизвестный рецензент";
        reviewId = "ID_Оценки";
    }

    public void setDetails(String title, String description, String reviewerName, String reviewId) {
        this.title = title;
        this.description = description;
        this.reviewerName = reviewerName;
        this.reviewId = reviewId;
    }

    public String getFullTitle() {
        return title + " (" + reviewerName + ")";
    }
}