package com.garage.parking.facility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


/**
 * Keeps information abut park.
 * Each area in the park corresponds {@link Slot}
 * @param <T> keeps information about parking object
 */
class Park<T> {
    private final Integer size;
    private List<Slot<T>> parkingLot;

    Park(Integer parkSize) {
        this.size = parkSize;
        this.setParkingLot(new ArrayList<>() {
            @Override
            public boolean add(Slot<T> slot) {
                if (this.size() >= size) {
                    throw new SlotIsFullException();
                }
                return super.add(slot);
            }

        });

        IntStream.range(0, size).forEach(value -> getParkingLot().add(new Slot<>()));
    }

    /**
     * look at the park and try to find a space for vehicle. if you find any space nearest the entrance, reserve the slots. Note: reserve one more slot to leave a blank space.
     *
     * @param size size of the vehicle
     *             Park do not know anything about vehicle, so integer should be used here.
     * @return Slot reserved slot. Also updates vehicle model in Slot
     */
    Optional<Slot<T>> tryReserve(Integer size) {
        int spaceCounter = size + 1; //(size + 1) means always reserve vehicle's right slot
        for (int i = 0; i < getParkingLot().size(); i++) {
            var slot = getParkingLot().get(i);

            if (slot.isReservable()) {
                spaceCounter--;
            } else {
                spaceCounter = size + 1; //(size + 1) means always reserve vehicle's right slot
            }

            //There is not enough space for vehicle
            if (spaceCounter != 0) {
                continue;
            }
            //There is enough space for vehicle
            int spaceLocation = i;
            //loop back to get beginning of the space
            while (spaceCounter++ <= size) {
                //reserve the space
                getParkingLot().get(spaceLocation--).setReservable(false);
            }
            //return th beginning of the space
            return Optional.of(getParkingLot().get(i - size));
        }
        return Optional.empty();
    }


    List<Slot<T>> getParkingLot() {
        return parkingLot;
    }

    private void setParkingLot(List<Slot<T>> parkingLot) {
        this.parkingLot = parkingLot;
    }
}
