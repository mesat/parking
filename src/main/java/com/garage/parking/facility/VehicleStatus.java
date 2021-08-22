package com.garage.parking.facility;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.garage.parking.Vehicle;

import java.io.Serializable;

/**
 * @author Muhammet Sakarya
 * created at 8/23/2021
 */
@JsonAutoDetect
public class VehicleStatus implements Serializable {
    private Vehicle vehicle;
    private Status status;

    public VehicleStatus(Vehicle vehicle, Status status) {
        this.setVehicle(vehicle);
        this.setStatus(status);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        RESERVED,
        PARKED;
    }

    @Override
    public String toString() {
        return (status != null ? status.toString() : "").concat(" ").concat(vehicle != null ? vehicle.toString() : " ");
    }
}