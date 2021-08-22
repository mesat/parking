package com.garage.parking;

import org.springframework.lang.NonNull;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
public enum Type {
    CAR(1),
    JEEP(2),
    TRUCK(4);

    private Integer size;

    Type(@NonNull Integer size) {
        this.size = size;

    }

    public Integer size() {
        return size;
    }

}
