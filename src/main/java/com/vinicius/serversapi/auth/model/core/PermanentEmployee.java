package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "servidor_efetivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class PermanentEmployee extends BaseEntity {

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Person person;

    @Column(name = "se_matricula", length = 20)
    private String registrationNumber;
}
