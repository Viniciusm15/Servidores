package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
