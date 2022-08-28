package com.damiano.parcel.app.repository;

import com.damiano.parcel.app.model.Parcel;
import org.springframework.data.repository.CrudRepository;

public interface ParcelRepository extends CrudRepository<Parcel, Long> {
}
