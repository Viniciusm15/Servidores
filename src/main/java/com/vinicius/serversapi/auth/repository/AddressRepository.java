package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
