package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class Address extends BaseEntity {

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
