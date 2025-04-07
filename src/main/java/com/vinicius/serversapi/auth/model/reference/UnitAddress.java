package com.vinicius.serversapi.auth.model.reference;

import com.vinicius.serversapi.auth.model.core.Address;
import com.vinicius.serversapi.auth.model.core.Unit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitAddress {

    @EmbeddedId
    private UnitAddressId id;

    @ManyToOne
    @MapsId("unitId")
    @JoinColumn(name = "unid_id")
    private Unit unit;

    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "end_id")
    private Address address;
}
