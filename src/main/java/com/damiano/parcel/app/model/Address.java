package com.damiano.parcel.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullPostCode;
    private String street;
    private String name;
    private String surname;
    private String homeNumber;

    public String getDistrict() {
        String[] parts = fullPostCode.strip().split(" ");
        String district = "";
        for (char ch : parts[0].toCharArray()) {
            if (Character.isDigit(ch)) {
                break;
            }
            district += ch;
        }
        return district;
    }
}
