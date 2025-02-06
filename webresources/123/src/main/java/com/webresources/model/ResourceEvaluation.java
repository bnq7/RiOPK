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
public class ResourceEvaluation {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User evaluator;   // Пользователь, который оценивает ресурс

    @ManyToOne(fetch = FetchType.LAZY)
    private Resource resource; // Ресурс, который оценивается

    private String evaluation; // Оценка или отзыв о ресурсе

    public ResourceEvaluation(User evaluator, Resource resource, String evaluation) {
        this.evaluator = evaluator;
        this.resource = resource;
        this.evaluation = evaluation;
    }
}