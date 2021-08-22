package com.garage.parking;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
@JsonAutoDetect
public class Vehicle implements Serializable {

    @JsonIgnore
    public static final String PLATE_NUMBER_FIELD = "plateNumber";
    @JsonIgnore
    public static final String COLOUR_FIELD = "colour";
    @JsonIgnore
    public static final String TYPE_FIELD = "type";

    private String plateNumber;
    private String colour;
    private Type type;

    /**
     * @param plateNumber
     * @param colour
     * @param type
     */
    public Vehicle(String plateNumber, String colour, Type type) {
        this.plateNumber = plateNumber;
        this.colour = colour;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.plateNumber == null) {
            return super.equals(obj);
        }
        if (!(obj instanceof Vehicle)) {
            return false;
        }
        return this.plateNumber.equals(((Vehicle) obj).plateNumber);
    }

    @Override
    public String toString() {
        return plateNumber + " ".concat(colour) + " ".concat(type == null ? "" : type.name());
    }
}
