package com.webresources.repo;

import com.webresources.model.ResourceProposal;  // Переименованная сущность для предложений
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepo extends JpaRepository<ResourceProposal, Long> {
}