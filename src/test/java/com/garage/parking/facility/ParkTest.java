package com.garage.parking.facility;

import com.garage.parking.Type;
import com.garage.parking.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {

    private Park<Vehicle> park;
    @BeforeEach
    void setUp() {
        park = new Park<>(10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void tryReserve() {
        var vehicleSlot = park.tryReserve(Type.JEEP.size());
        Assertions.assertTrue(vehicleSlot.isPresent());

        vehicleSlot = park.tryReserve(Type.TRUCK.size());
        Assertions.assertTrue(vehicleSlot.isPresent());

        vehicleSlot = park.tryReserve(Type.TRUCK.size());
        Assertions.assertTrue(vehicleSlot.isEmpty());

        vehicleSlot = park.tryReserve(Type.CAR.size());
        Assertions.assertTrue(vehicleSlot.isPresent());

        vehicleSlot = park.tryReserve(Type.TRUCK.size());
        Assertions.assertTrue(vehicleSlot.isEmpty());

    }

}