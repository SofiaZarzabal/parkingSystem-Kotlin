package com.example.parkingsystemkotlin.util

import com.example.parkingsystemkotlin.database.ParkingSpaceReservationDB
import com.example.parkingsystemkotlin.entity.Reservation

class ReservationChecker(var database: ParkingSpaceReservationDB) {

    fun canBeReserved(reservation: Reservation): Boolean {
        val parkingSpace = reservation.parkingSpace
        if (database.hashReservation.containsKey(parkingSpace)) {
            val reservations: MutableList<Reservation>? = database.hashReservation[parkingSpace]
            return reservations?.none { isOverlap(reservation, it) } == true
        }
        return true
    }

    private fun isOverlap(reservation: Reservation, storedReservation: Reservation): Boolean {
        val reservationStart = reservation.getDateAndTimeStart()
        val reservationEnd = reservation.getDateAndTimeEnd()
        val storedReservationStart = storedReservation.getDateAndTimeStart()
        val storedReservationEnd = storedReservation.getDateAndTimeEnd()
        return when {
            reservationStart.before(storedReservationStart) && reservationEnd.after(storedReservationStart) -> true
            reservationStart.before(storedReservationEnd) && reservationEnd.after(storedReservationEnd) -> true
            else -> reservationStart.after(storedReservationStart) && reservationEnd.before(storedReservationEnd)
        }
    }
}
