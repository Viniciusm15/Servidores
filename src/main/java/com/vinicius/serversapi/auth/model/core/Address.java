package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long id;

    @Column(name = "end_tipo_logradouro", length = 50)
    private String streetType;

    @Column(name = "end_logradouro", length = 200)
    private String streetName;

    @Column(name = "end_numero", length = 20)
    private String number;

    @Column(name = "end_bairro", length = 100)
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private City city;
}
