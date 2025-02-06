package com.webresources.model;

import com.webresources.model.enums.EvaluationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EvaluationCriteria {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;  // Название критерия оценки (например, "Пользовательский интерфейс", "Надежность", и т.д.)
    private int minExperience;  // Минимальный опыт, если нужно учитывать опыт оценки
    private int salaryRange;    // Статус может быть представлен, например, в виде ценового диапазона или уровня
    @Enumerated(EnumType.STRING)
    private EvaluationStatus status;  // Статус критерия (например, "Ожидает", "Активен", "Завершен")
    @OneToMany(mappedBy = "evaluationCriteria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;  // Оценки, связанные с этим критерием

    public EvaluationCriteria(String name, int minExperience, int salaryRange) {
        this.name = name;
        this.minExperience = minExperience;
        this.salaryRange = salaryRange;
        this.status = EvaluationStatus.PENDING;  // Изначально статус "Ожидает"
    }
}