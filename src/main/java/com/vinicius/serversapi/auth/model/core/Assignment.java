package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private Unit unit;

    @Column(name = "lot_data_lotacao")
    private LocalDate assignmentDate;

    @Column(name = "lot_data_remocao")
    private LocalDate removalDate;

    @Column(name = "lot_portaria", length = 100)
    private String ordinance;
}
