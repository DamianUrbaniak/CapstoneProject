package com.damiano.parcel.app.controller;

import com.damiano.parcel.app.ParcelGrouper;
import com.damiano.parcel.app.model.Parcel;
import com.damiano.parcel.app.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parcel")
public class ParcelController {

    private final ParcelRepository parcelRepository;
    private final ParcelGrouper parcelGrouper;

    @Autowired
    public ParcelController(ParcelRepository parcelRepository, ParcelGrouper parcelGrouper) {
        this.parcelRepository = parcelRepository;
        this.parcelGrouper = parcelGrouper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createParcel(@RequestBody Parcel parcel) {
        Parcel persisted = parcelRepository.save(parcel);
        return persisted.getId();
    }

    @DeleteMapping("/{id}")
    public void deleteParcel(@PathVariable("id") Long id) {
        parcelRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcel> getParcel(@PathVariable("id") Long id) {
        return ResponseEntity.of(parcelGrouper.getParcelById(id));
    }

    @GetMapping
    public Iterable<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }

    @GetMapping("/byDistrict/{district}")
    public Iterable<Parcel> getParcelsByDistrict(@PathVariable("district") String district) {
        return parcelGrouper.getParcelsByDistrict(district);
    }

    @GetMapping("/byDistrict")
    public Map<String, List<Parcel>> getAllParcelsByDistrict() {
        return parcelGrouper.getGroupedParcels();
    }
}
