package com.garage.parking;

import com.garage.parking.facility.VehicleStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Muhammet Sakarya
 * created at 8/22/2021
 */
public class ParkStatus {
    private Map<VehicleStatus, List<Integer>> location;
    public ParkStatus(){
        location = new LinkedHashMap<>();
    }

    public Map<VehicleStatus, List<Integer>> getLocation() {
        return location;
    }

    public void setLocation(Map<VehicleStatus, List<Integer>> location) {
        this.location = location;
    }

}
