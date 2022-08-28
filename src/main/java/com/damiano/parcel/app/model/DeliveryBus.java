package com.damiano.parcel.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class DeliveryBus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private final List<Driver> drivers = new ArrayList<>();
    @OneToMany
    private final List<Parcel> parcels = new ArrayList<>();
    private Double weightCapacity;
    private String plateNumber;

    public DeliveryBus(double weightCapacity, String plateNumber) {
        this.weightCapacity = weightCapacity;
        this.plateNumber = plateNumber;
    }

    public void addParcels(Iterable<Parcel> parcelsToAdd) {
        for (Parcel parcel : parcelsToAdd) {
            parcels.add(parcel);
        }
    }

    public void assignDriver(Driver driver) {
        this.drivers.add(driver);
    }

    public void removeDriver(Driver driver) {
        this.drivers.remove(driver);
    }

    public void removeDriverById(Long driverId) {
        Driver driverToRemove = findDriverById(driverId);
        drivers.remove(driverToRemove);
    }

    private Driver findDriverById(Long driverId) {
        for (Driver driver : drivers) {
            if (driver.getId().equals(driverId)) {
                return driver;
            }
        }
        return null;
    }
}
