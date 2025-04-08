package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class TemporaryEmployee extends BaseEntity {

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Person person;

    @Column(name = "st_data_admissao")
    private LocalDate admissionDate;

    @Column(name = "st_data_demissao")
    private LocalDate dismissalDate;
}
