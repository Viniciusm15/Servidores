package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
