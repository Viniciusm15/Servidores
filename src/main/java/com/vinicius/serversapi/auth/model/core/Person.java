package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.User;
import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class Person extends BaseEntity {

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

    @OneToMany(mappedBy = "person")
    private List<Assignment> assignmentList;

    @OneToMany(mappedBy = "person")
    private List<PersonPhoto> photos;

    @OneToOne(mappedBy = "person")
    private PermanentEmployee permanentEmployee;
}
