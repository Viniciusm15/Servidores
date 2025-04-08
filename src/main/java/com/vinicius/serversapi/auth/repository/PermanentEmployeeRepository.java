package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.PermanentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermanentEmployeeRepository extends JpaRepository<PermanentEmployee, Long> {
}
