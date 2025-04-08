package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class PersonPhoto extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Person person;

    @Column(name = "fp_data")
    private LocalDate date;

    @Column(name = "fp_bucket", length = 30)
    private String bucket;

    @Column(name = "fp_hash", length = 50)
    private String hash;
}
