package com.vinicius.serversapi.auth.model.core;

import com.vinicius.serversapi.auth.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "cidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
public class City extends BaseEntity {

    @Column(name = "cid_nome", length = 200)
    private String name;

    @Column(name = "cid_uf", length = 2)
    private String state;
}
