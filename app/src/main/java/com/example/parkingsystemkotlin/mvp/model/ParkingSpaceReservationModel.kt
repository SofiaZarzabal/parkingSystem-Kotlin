package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.database.ParkingSpaceReservationDB
import com.example.parkingsystemkotlin.entity.Reservation
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.util.DateUtils
import com.example.parkingsystemkotlin.util.ReservationChecker
import com.example.parkingsystemkotlin.util.ReservationVerifiyResult
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ParkingSpaceReservationModel : ParkingSpaceReservationContract.ParkingSpaceReservationModel {
    private var isDateStartButtonPressed = false
    private val reservation = Reservation()
    private val reservationChecker = ReservationChecker(ParkingSpaceReservationDB)

    override fun setDateStartButtonPressed(isDateStartButtonPressed: Boolean) {
        this.isDateStartButtonPressed = isDateStartButtonPressed
    }

    override fun convertToCalendar(sDate: String, formatGiven: String): Calendar {
        val format = SimpleDateFormat(formatGiven, Locale.getDefault())
        return DateUtils.convertToCalendar(sDate, format)
    }

    override fun getDateStartButtonPressed(): Boolean = isDateStartButtonPressed

    override fun setDateStart(dateStart: Calendar) {
        reservation.dateStart = dateStart
    }

    override fun setTimeStart(timeStart: Calendar) {
        reservation.timeStart = timeStart
    }

    override fun setDateEnd(dateEnd: Calendar) {
        reservation.dateEnd = dateEnd
    }

    override fun setTimeEnd(timeEnd: Calendar) {
        reservation.timeEnd = timeEnd
    }

    override fun getDateStart(): Calendar = reservation.dateStart

    override fun getTimeStart(): Calendar = reservation.timeStart

    override fun getDateEnd(): Calendar = reservation.dateEnd

    override fun getTimeEnd(): Calendar = reservation.timeEnd

    override fun getFormattedString(calendar: Calendar, formatGiven: String): String = reservation.getFormattedString(calendar, formatGiven)

    override fun getReservation(): Reservation = reservation

    override fun completeReservationInfo(parkingSpace: Int, securityCode: Int) {
        reservation.parkingSpace = parkingSpace
        reservation.securityCode = securityCode
    }

    override fun getReservationVerifyResult(): ReservationVerifiyResult = reservation.getReservationVerifyResult()

    override fun getValidReservation(): ReservationVerifiyResult =
        if (reservationChecker.canBeReserved(reservation)) {
            ReservationVerifiyResult.SUCCESS
        } else {
            ReservationVerifiyResult.RESERVATION_OVERLAPPING
        }

    override fun makeReservation(reservation: Reservation) {
        ParkingSpaceReservationDB.addReservation(reservation)
    }
}
