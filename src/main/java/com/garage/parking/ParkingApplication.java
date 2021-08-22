package com.garage.parking;

import com.garage.parking.facility.ParkingFacility;
import com.garage.parking.facility.ParkingFacilityImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Value(value = "${sizeOfThePark}")
    Integer sizeOfThePark;
    @Bean
    public ParkingFacility createParkingFacility(){
        return new ParkingFacilityImpl(sizeOfThePark);
    }

}
