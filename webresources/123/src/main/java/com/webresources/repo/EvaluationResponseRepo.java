package com.webresources.repo;

import com.webresources.model.EvaluationResponse;  // Переименованное имя сущности
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationResponseRepo extends JpaRepository<EvaluationResponse, Long> {
}