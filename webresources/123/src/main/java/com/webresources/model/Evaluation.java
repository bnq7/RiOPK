package com.webresources.model;

import com.webresources.model.enums.EvaluationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Evaluation {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationObject evaluationObject;  // Объект, который оценивается (например, сайт, сервис и т.д.)

    @ManyToOne(fetch = FetchType.LAZY)
    private User evaluator;  // Пользователь, который оценивает

    private EvaluationStatus status;  // Статус оценки (например, "Ожидает", "Одобрено", "Отклонено")

    public Evaluation(EvaluationObject evaluationObject, User evaluator) {
        this.evaluationObject = evaluationObject;
        this.evaluator = evaluator;
        this.status = EvaluationStatus.PENDING;  // Изначально статус "Ожидает"
    }
}