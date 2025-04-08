package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.TemporaryEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryEmployeeRepository extends JpaRepository<TemporaryEmployee, Long> {
}
