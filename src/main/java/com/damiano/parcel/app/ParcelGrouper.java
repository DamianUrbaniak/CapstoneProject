package com.damiano.parcel.app;

import com.damiano.parcel.app.model.Parcel;
import com.damiano.parcel.app.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class ParcelGrouper {

    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelGrouper(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public Optional<Parcel> getParcelById(Long id) {
        return parcelRepository.findById(id);
    }

    public Iterable<Parcel> getParcelsByDistrict(String district) {
        Iterable<Parcel> allParcels = parcelRepository.findAll();
        Stream<Parcel> stream = StreamSupport.stream(allParcels.spliterator(), false);
        return stream.filter(p -> p.getDistrict().equals(district)).collect(Collectors.toList());
    }

    public Map<String, List<Parcel>> getGroupedParcels() {
        Iterable<Parcel> allParcels = parcelRepository.findAll();
        Stream<Parcel> stream = StreamSupport.stream(allParcels.spliterator(), false);
        return stream.collect(Collectors.groupingBy(Parcel::getDistrict));
    }
}
