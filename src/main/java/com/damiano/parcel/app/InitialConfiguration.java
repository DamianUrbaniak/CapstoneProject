package com.damiano.parcel.app;

import com.damiano.parcel.app.model.DeliveryBus;
import com.damiano.parcel.app.model.Driver;
import com.damiano.parcel.app.repository.DeliveryBusRepository;
import com.damiano.parcel.app.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InitialConfiguration {

    @Bean
    public CommandLineRunner dataLoader(DriverRepository driverRepository, DeliveryBusRepository deliveryBusRepository) {
        return args -> {
            if (driverRepository.count() != 0 || deliveryBusRepository.count() != 0) {
                return;
            }

            Driver d1 = new Driver("Adam", "Wielkanocny");
            Driver d2 = new Driver("Jan", "Kot");
            Driver d3 = new Driver("Tomasz", "Robak");
            driverRepository.save(d1);
            driverRepository.save(d2);
            driverRepository.save(d3);

            DeliveryBus db1 = new DeliveryBus(400.0, "PO P350A");
            db1.assignDriver(d1);
            db1.assignDriver(d2);
            deliveryBusRepository.save(db1);

            DeliveryBus db2 = new DeliveryBus(210.2, "GM 31D27");
            db2.assignDriver(d3);
            deliveryBusRepository.save(db2);
        };
    }
}
