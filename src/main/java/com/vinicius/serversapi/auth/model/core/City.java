package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    private Long id;

    @Column(name = "cid_nome", length = 200)
    private String name;

    @Column(name = "cid_uf", length = 2)
    private String state;
}
