package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemporaryEmployee {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "st_data_admissao")
    private LocalDate admissionDate;

    @Column(name = "st_data_demissao")
    private LocalDate dismissalDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Person person;
}
