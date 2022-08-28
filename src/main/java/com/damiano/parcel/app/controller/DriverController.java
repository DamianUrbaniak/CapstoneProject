package com.damiano.parcel.app.controller;

import com.damiano.parcel.app.model.Driver;
import com.damiano.parcel.app.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createDriver(@RequestBody Driver driver) {
        Driver persisted = driverRepository.save(driver);
        return persisted.getId();
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        driverRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable("id") Long id) {
        return ResponseEntity.of(driverRepository.findById(id));
    }

    @GetMapping
    public Iterable<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}
