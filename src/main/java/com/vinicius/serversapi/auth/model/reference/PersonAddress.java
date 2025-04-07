package com.vinicius.serversapi.auth.model.reference;

import com.vinicius.serversapi.auth.model.core.Address;
import com.vinicius.serversapi.auth.model.core.Person;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pessoa_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonAddress {

    @EmbeddedId
    private PersonAddressId id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "pes_id")
    private Person person;

    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "end_id")
    private Address address;
}
