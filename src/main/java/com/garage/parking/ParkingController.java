package com.garage.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Muhammet Sakarya
 * created at 8/21/2021
 */
@RestController
public class ParkingController {

    private ParkingEnforcer parkingEnforcer;

    @Autowired
    public ParkingController(ParkingEnforcer parkingEnforcer) {
        this.parkingEnforcer = parkingEnforcer;
    }


    /**
     * Reserve a proper space for he vehicle if it possible or throws exceptions.
     *  get a vehicle model and create a ticket using jwt.
     *  Enterance is like a login screen.
     * @param vehicle
     * @return a created ticked just like token
     */
    @PostMapping(path = "/reserve")
    public String reservePark(@RequestBody @NonNull Vehicle vehicle){
        assertVehicle(vehicle);
        return parkingEnforcer.reserve(vehicle);

    }

    /**
     * Park the vehicle to reserved area
     * @param ticket returned value from {reservePark}
     */
    @GetMapping(path = "/park")
    public void parkTheVehicle(@RequestParam("ticket") String ticket){
        parkingEnforcer.park(ticket);

    }
    @GetMapping(path = "/leave")
    public void leavePark(@RequestParam("ticket") String ticket){
        parkingEnforcer.leave(ticket);

    }
    @PostMapping(path = "/status")
    public ParkStatus getParkStatus(){
        return parkingEnforcer.status();


    }

    private void assertVehicle(Vehicle vehicle){
        Assert.notNull(vehicle.getType(),"Vehicle type cannot be null");
        Assert.notNull(vehicle.getColour(),"Vehicle colour cannot be null");
        Assert.notNull(vehicle.getPlateNumber(),"Vehicle plate number cannot be null");

    }

    @ExceptionHandler
    public ResponseEntity<String> commonHandler(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.toString() + " " + ex.getMessage());
    }
}
