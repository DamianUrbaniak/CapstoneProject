package com.damiano.parcel.app.controller;

import com.damiano.parcel.app.ParcelGrouper;
import com.damiano.parcel.app.model.DeliveryBus;
import com.damiano.parcel.app.model.Driver;
import com.damiano.parcel.app.model.Parcel;
import com.damiano.parcel.app.repository.DeliveryBusRepository;
import com.damiano.parcel.app.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deliveryBus")
public class DeliveryBusController {

    private final DeliveryBusRepository deliveryBusRepository;
    private final DriverRepository driverRepository;
    private final ParcelGrouper parcelGrouper;

    @Autowired
    public DeliveryBusController(DeliveryBusRepository deliveryBusRepository, DriverRepository driverRepository, ParcelGrouper parcelGrouper) {
        this.deliveryBusRepository = deliveryBusRepository;
        this.driverRepository = driverRepository;
        this.parcelGrouper = parcelGrouper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBus(@RequestBody DeliveryBus bus) {
        DeliveryBus persisted = deliveryBusRepository.save(bus);
        return persisted.getId();
    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable("id") Long id) {
        deliveryBusRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBus> getBus(@PathVariable("id") Long id) {
        return ResponseEntity.of(deliveryBusRepository.findById(id));
    }

    @GetMapping
    public Iterable<DeliveryBus> getAllBusses() {
        return deliveryBusRepository.findAll();
    }

    @PostMapping("/{busId}/driver/{driverId}")
    public void assignDriver(@PathVariable("busId") Long busId, @PathVariable("driverId") Long driverId) {
        Optional<DeliveryBus> optBus = deliveryBusRepository.findById(busId);
        if (optBus.isEmpty()) {
            return;
        }

        Optional<Driver> optDriver = driverRepository.findById(driverId);
        if (optDriver.isEmpty()) {
            return;
        }

        DeliveryBus deliveryBus = optBus.get();
        Driver driver = optDriver.get();
        deliveryBus.assignDriver(driver);
        deliveryBusRepository.save(deliveryBus);
    }

    @DeleteMapping("/{busId}/driver/{driverId}")
    public void removeDriver(@PathVariable("busId") Long busId, @PathVariable("driverId") Long driverId) {
        Optional<DeliveryBus> optBus = deliveryBusRepository.findById(busId);
        if (optBus.isEmpty()) {
            return;
        }

        DeliveryBus deliveryBus = optBus.get();
        deliveryBus.removeDriverById(driverId);
        deliveryBusRepository.save(deliveryBus);
    }

    @PostMapping("/{busId}/parcel/{parcelId}")
    public void assignParcel(@PathVariable("busId") Long busId, @PathVariable("parcelId") Long parcelId) {
        Optional<DeliveryBus> optBus = deliveryBusRepository.findById(busId);
        if (optBus.isEmpty()) {
            return;
        }

        Optional<Parcel> optParcel = parcelGrouper.getParcelById(parcelId);
        if (optParcel.isEmpty()) {
            return;
        }

        DeliveryBus deliveryBus = optBus.get();
        Parcel parcel = optParcel.get();
        deliveryBus.addParcels(List.of(parcel));
        deliveryBusRepository.save(deliveryBus);
    }

    @PostMapping("/{busId}/parcel/byDistrict/{district}")
    public void assignAllParcelsFromDistrict(@PathVariable("busId") Long busId, @PathVariable("district") String district) {
        Optional<DeliveryBus> optBus = deliveryBusRepository.findById(busId);
        if (optBus.isEmpty()) {
            return;
        }

        DeliveryBus deliveryBus = optBus.get();
        Iterable<Parcel> districtParcels = parcelGrouper.getParcelsByDistrict(district);
        deliveryBus.addParcels(districtParcels);
        deliveryBusRepository.save(deliveryBus);
    }
}
