package com.garage.parking.facility;

/**
 * @author Muhammet Sakarya
 * created at 8/22/2021
 */
class Slot<T> {
    /**
     * slot may be emty
     */
    private boolean empty = true;
    /**
     * Although the slot is empty, it may not be reservable.
     */
    private boolean reservable = true;
    private T object;

    public boolean getEmty() {
        return empty;
    }

    void setEmty(Boolean emty) {
        this.empty = emty;
        reservable = false;
    }

    boolean isReservable() {
        return reservable;
    }

    void setReservable(boolean reservable) {
        this.reservable = reservable;
    }

    T getObject() {
        return object;
    }

    void setObject(T object) {
        this.object = object;
    }
}
