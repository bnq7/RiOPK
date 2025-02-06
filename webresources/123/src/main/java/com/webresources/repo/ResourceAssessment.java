package com.webresources.repo;

import com.webresources.model.ResourceAssessment;  // Переименованная сущность для оценки
import com.webresources.model.enums.AssessmentStatus;  // Переименованный статус
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceAssessment extends JpaRepository<ResourceAssessment, Long> {
    List<ResourceAssessment> findAllByStatus(AssessmentStatus status);  // Найти все оценки по статусу

    ResourceAssessment findByCriteria_IdAndEvaluator_Id(Long criteriaId, Long evaluatorId);  // Найти оценку по критерию и оценщику
}