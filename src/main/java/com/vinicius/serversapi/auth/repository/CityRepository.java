package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
