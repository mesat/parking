package com.garage.parking.facility;

import com.garage.parking.ParkStatus;
import com.garage.parking.Vehicle;

/**
 * @author Muhammet Sakarya
 * created at 8/22/2021
 */
public interface ParkingFacility {
    void reserve(Vehicle vehicle) throws ParkIsFullException;

    void park(Vehicle vehicle) throws VehicleNotReservedException;

    void leave(Vehicle vehicle) throws VehicleNotReservedException;

    ParkStatus status();

}
