package com.garage.parking;

import com.garage.parking.facility.ParkIsFullException;
import com.garage.parking.facility.ParkingFacility;
import com.garage.parking.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Muhammet Sakarya
 * created at 8/22/2021
 * Manages park IO
 */
@Component
public class ParkingEnforcerImpl implements ParkingEnforcer {

    private ParkingFacility parkingFacility;
    private TicketService ticketService;

    @Autowired
    ParkingEnforcerImpl(ParkingFacility parkingFacility, TicketService ticketService){
        this.parkingFacility = parkingFacility;
        this.ticketService = ticketService;
    }
    @ExceptionHandler
    public ResponseEntity ex(ParkIsFullException ex) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public String reserve(Vehicle vehicle) {
        parkingFacility.reserve(vehicle);
        return ticketService.generateToken(vehicle);

    }

    @Override
    public void park(String ticket) {
        parkingFacility.park(ticketService.parseToken(ticket));

    }

    @Override
    public void leave(String ticket) {
        parkingFacility.leave(ticketService.parseToken(ticket));

    }

    @Override
    public ParkStatus status() {
        return parkingFacility.status();
    }
}
