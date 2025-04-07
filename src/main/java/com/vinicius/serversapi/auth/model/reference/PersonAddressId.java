package com.vinicius.serversapi.auth.model.reference;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonAddressId implements Serializable {

    @Column(name = "pes_id")
    private Long personId;

    @Column(name = "end_id")
    private Long addressId;
}
