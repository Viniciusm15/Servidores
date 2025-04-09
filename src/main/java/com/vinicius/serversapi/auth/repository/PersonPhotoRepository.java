package com.vinicius.serversapi.auth.repository;

import com.vinicius.serversapi.auth.model.core.PersonPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonPhotoRepository extends JpaRepository<PersonPhoto, Long> {
    Page<PersonPhoto> findByPersonId(Long personId, Pageable pageable);
}
