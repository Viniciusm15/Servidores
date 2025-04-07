package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servidor_efetivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermanentEmployee {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "se_matricula", length = 20)
    private String registrationNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Person person;
}
