package com.example.parkingsystemkotlin.database

import com.example.parkingsystemkotlin.entity.Reservation
import java.util.Calendar

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

    fun releasePastReservations() {
        hashReservation.forEach { (reservationList, _) ->
            hashReservation[reservationList]?.removeAll { reservation -> Calendar.getInstance().after(reservation.dateEnd) }
            if (hashReservation[reservationList].isNullOrEmpty()) {
                hashReservation.remove(reservationList)
            }
        }
    }

    fun getAllReservations(): List<Reservation> = hashReservation.flatMap { (_, values) -> values }
}
