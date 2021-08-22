package com.garage.parking.ticket;

import com.garage.parking.Type;
import com.garage.parking.Vehicle;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.gson.io.GsonDeserializer;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
@Component
public class JwtTicketService implements TicketService {
    @Value("${jwtSecret}")
    private String JWTSECRET;
    private Gson gson = new Gson();

    @Override
    public String generateToken(Vehicle vehicle) {

        Key key = Keys.hmacShaKeyFor(JWTSECRET.getBytes());
        return Jwts.builder().serializeToJsonWith(new GsonSerializer(gson))
                .claim(Vehicle.PLATE_NUMBER_FIELD, vehicle.getPlateNumber())
                .claim(Vehicle.COLOUR_FIELD, vehicle.getColour())
                .claim(Vehicle.TYPE_FIELD, vehicle.getType())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Vehicle parseToken(String ticket) {
        byte[] secretBytes = JWTSECRET.getBytes();

        Jws<Claims> jwsClaims = Jwts.parserBuilder().deserializeJsonWith(new GsonDeserializer(gson))
                .setSigningKey(secretBytes)
                .build()
                .parseClaimsJws(ticket);

        String plateNumber = jwsClaims.getBody()
                .get(Vehicle.PLATE_NUMBER_FIELD, String.class);
        String colour = jwsClaims.getBody().get(Vehicle.COLOUR_FIELD, String.class);
        String type = jwsClaims.getBody().get(Vehicle.TYPE_FIELD, String.class);

        return new Vehicle(plateNumber, colour, Type.valueOf(type));
    }
}
