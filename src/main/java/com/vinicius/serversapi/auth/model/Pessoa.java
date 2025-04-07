package com.vinicius.serversapi.auth.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "pes_nome", length = 200)
    private String nome;

    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo", length = 1)
    private String sexo;

    @Column(name = "pes_mae", length = 200)
    private String nomeMae;

    @Column(name = "pes_pai", length = 200)
    private String nomePai;

    @OneToOne(mappedBy = "pessoa")
    private User user;
}

