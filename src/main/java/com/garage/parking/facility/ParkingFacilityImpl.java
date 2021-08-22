package com.garage.parking.facility;

import com.garage.parking.ParkStatus;
import com.garage.parking.Vehicle;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Muhammet Sakarya
 * created at 8/22/2021
 */
public class ParkingFacilityImpl implements ParkingFacility {

    private Park<Vehicle> otopark;

    public ParkingFacilityImpl(Integer size) {
        otopark = new Park<>(size);
    }


    @Override
    public void reserve(Vehicle vehicle) throws ParkIsFullException {
        final boolean empty = otopark.getParkingLot().stream()
                .filter(vehicleSlot -> vehicleSlot.getObject() != null)
                .anyMatch(vehicleSlot -> vehicle.getPlateNumber().equals(vehicleSlot.getObject().getPlateNumber()));
        if (empty) {
            throw new AlreadyReservedException();
        }
        var result = otopark.tryReserve(vehicle.getType().size());
        result.orElseThrow(ParkIsFullException::new).setObject(vehicle);
    }

    @Override
    public void park(Vehicle vehicle) throws VehicleNotReservedException {
        final var startingSlot = otopark.getParkingLot().stream()
                .filter(vehicleSlot -> vehicle.equals(vehicleSlot.getObject()))
                .findFirst()
                .orElseThrow(VehicleNotReservedException::new);

        Integer startIndex = otopark.getParkingLot().indexOf(startingSlot);
        IntStream.range(startIndex, startIndex + startingSlot.getObject().getType().size()).forEach(value -> {
            otopark.getParkingLot().get(value).setEmty(false);
        });
    }

    @Override
    public void leave(Vehicle vehicle) throws VehicleNotReservedException {
        final var startingSlot = otopark.getParkingLot().stream()
                .filter(vehicleSlot -> vehicle.equals(vehicleSlot.getObject()))
                .findFirst()
                .orElseThrow(VehicleNotReservedException::new);
        int startIndex = otopark.getParkingLot().indexOf(startingSlot);
        IntStream.range(startIndex, 1 + startIndex + startingSlot.getObject().getType().size()).forEach(value -> {
            otopark.getParkingLot().get(value).setEmty(true);
            otopark.getParkingLot().get(value).setReservable(true);
        });
        startingSlot.setObject(null);
    }

    @Override
    public ParkStatus status() {
        ParkStatus parkStatus = new ParkStatus();
        otopark.getParkingLot().stream().filter(vehicleSlot -> vehicleSlot.getObject() != null)
                .forEach(vehicleSlot -> {
                    final int indexOf = otopark.getParkingLot().indexOf(vehicleSlot);
                    if (vehicleSlot.getObject() == null) {
                        return;
                    }
                    parkStatus.getLocation().put(
                            new VehicleStatus(
                                    vehicleSlot.getObject(),
                                    vehicleSlot.getEmty()
                                            ? VehicleStatus.Status.RESERVED
                                            : VehicleStatus.Status.PARKED)
                            ,
                            IntStream.range(indexOf,
                                    indexOf + vehicleSlot.getObject().getType().size()).boxed().collect(Collectors.toList()));
                });
        return parkStatus;
    }
}
