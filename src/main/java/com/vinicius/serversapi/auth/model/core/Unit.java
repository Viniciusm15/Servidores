package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome", length = 200)
    private String name;

    @Column(name = "unid_sigla", length = 20)
    private String acronym;
}
