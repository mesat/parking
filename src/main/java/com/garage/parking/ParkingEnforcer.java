package com.garage.parking;

/**
 * @author Muhammet Sakarya
 * created at 8/22/2021
 */
public interface ParkingEnforcer {

    String reserve(Vehicle vehicle);

    void park(String ticket) ;

    void leave(String ticket) ;

    ParkStatus status();
}
