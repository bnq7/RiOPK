package com.webresources.repo;

import com.webresources.model.ResourceFeedback;  // Переименованная сущность для отзывов
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFeedback extends JpaRepository<ResourceFeedback, Long> {
}