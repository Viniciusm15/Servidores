package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Page<Assignment> findActiveWithPermanentEmployeeByUnitId(Long unitId, Pageable pageable);
}
