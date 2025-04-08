package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
