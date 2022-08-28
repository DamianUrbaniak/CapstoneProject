package com.damiano.parcel.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    private String receiverContactNumber;
    private String senderContactNumber;
    private Double weight;

    public String getDistrict() {
        return address.getDistrict();
    }
}
