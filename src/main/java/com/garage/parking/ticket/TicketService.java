package com.garage.parking.ticket;

import com.garage.parking.Vehicle;
import org.springframework.context.annotation.Scope;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
@Scope("singleton")
public interface TicketService {

    String generateToken(Vehicle vehicle);

    Vehicle parseToken(String ticket);
}
