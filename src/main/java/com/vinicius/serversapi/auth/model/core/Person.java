package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "pes_nome", length = 200)
    private String name;

    @Column(name = "pes_data_nascimento")
    private LocalDate birthDate;

    @Column(name = "pes_sexo", length = 9)
    private String gender;

    @Column(name = "pes_mae", length = 200)
    private String motherName;

    @Column(name = "pes_pai", length = 200)
    private String fatherName;

    @OneToOne(mappedBy = "person")
    private User user;
}
