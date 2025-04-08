package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "unidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class Unit extends BaseEntity {

    @Column(name = "unid_nome", length = 200)
    private String name;

    @Column(name = "unid_sigla", length = 20)
    private String acronym;
}
