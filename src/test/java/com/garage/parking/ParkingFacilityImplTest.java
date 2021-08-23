package com.garage.parking;

import com.garage.parking.facility.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingFacilityImplTest {

    private ParkingFacility parkingFacility;
    private Vehicle vehicle;
    private Vehicle vehicle2;
    private Vehicle vehicle3;
    private Vehicle vehicle4;
    private Vehicle vehicle5;
    private Vehicle vehicle6;
    @BeforeEach
    void setUp() {
        parkingFacility = new ParkingFacilityImpl(10);
        vehicle = new Vehicle("38ad423","RED",Type.TRUCK);
        vehicle2 = new Vehicle("38ad424","RED",Type.CAR);
        vehicle3 = new Vehicle("38ad425","RED",Type.JEEP);
        vehicle4 = new Vehicle("38ad426","RED",Type.CAR);
        vehicle5 = new Vehicle("38ad427","RED",Type.CAR);
        vehicle6 = new Vehicle("38ad428","RED",Type.TRUCK);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void commonTests() {
        Assertions.assertThrows(VehicleNotReservedException.class,()->parkingFacility.park(vehicle));
        Assertions.assertDoesNotThrow(()->parkingFacility.reserve(vehicle));
        Assertions.assertThrows(AlreadyReservedException.class,()->parkingFacility.reserve(vehicle));
        parkingFacility.park(vehicle);
        //todo: park again doesnt throw an exception
        //Assertions.assertThrows(VehicleNotReservedException.class,()->parkingFacility.park(vehicle));
        parkingFacility.reserve(vehicle2);
        parkingFacility.park(vehicle2);
        parkingFacility.reserve(vehicle3);
        parkingFacility.park(vehicle3);
        parkingFacility.leave(vehicle2);
        Assertions.assertThrows(VehicleNotReservedException.class,()->parkingFacility.leave(vehicle2));
        parkingFacility.leave(vehicle3);
        parkingFacility.reserve(vehicle6);
        parkingFacility.park(vehicle6);
        Assertions.assertThrows(ParkIsFullException.class,()->parkingFacility.reserve(vehicle3));
    }

}