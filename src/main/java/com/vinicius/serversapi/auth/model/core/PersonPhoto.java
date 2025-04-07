package com.vinicius.serversapi.auth.model.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Person person;

    @Column(name = "fp_data")
    private LocalDate date;

    @Column(name = "fp_bucket", length = 30)
    private String bucket;

    @Column(name = "fp_hash", length = 50)
    private String hash;
}
