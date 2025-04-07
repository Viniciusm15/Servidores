package com.vinicius.serversapi.auth.model.reference;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitAddressId implements java.io.Serializable {

    @Column(name = "unid_id")
    private Long unitId;

    @Column(name = "end_id")
    private Long addressId;
}
