package com.example.parkingsystemkotlin.database

import com.example.parkingsystemkotlin.entity.Reservation

object ParkingSpaceReservationDB {
    val hashReservation: MutableMap<Int, MutableList<Reservation>> = HashMap()

    fun addReservation(reservation: Reservation) {
        var reservations: MutableList<Reservation>? = mutableListOf<Reservation>()
        val parkingSpace = reservation.parkingSpace
        if (hashReservation.containsKey(parkingSpace)) {
            reservations = hashReservation[parkingSpace]
        }
        reservations?.let {
            it.add(reservation)
            hashReservation.put(parkingSpace, it)
        }
    }
}
