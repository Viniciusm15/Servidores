package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class Assignment extends BaseEntity {

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
