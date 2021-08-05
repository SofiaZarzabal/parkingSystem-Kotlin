package com.example.parkingsystemkotlin.mvp.contract

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.entity.Reservation
import com.example.parkingsystemkotlin.util.ReservationVerifiyResult
import java.util.Calendar

interface ParkingSpaceReservationContract {

    interface ParkingSpaceReservationPresenter {
        fun onButtonParkingSpaceReservationPickerPressed(listener: DatePickerDialog.OnDateSetListener)
        fun onDateSetPressed(year: Int, month: Int, dayOfMonth: Int, timeListener: TimePickerDialog.OnTimeSetListener)
        fun onTimeSetPressed(hourOfDay: Int, minute: Int)
        fun onButtonParkingSpaceReservationSavePressed()
        fun clearOldReservations()
        fun onButtonParkingSpaceReservationListPressed()
    }

    interface ParkingSpaceReservationModel {
        fun setDateStartButtonPressed(isDateStartButtonPressed: Boolean)
        fun getDateStartButtonPressed(): Boolean
        fun convertToCalendar(sDate: String, formatGiven: String): Calendar
        fun setDateStart(dateStart: Calendar)
        fun setTimeStart(timeStart: Calendar)
        fun setDateEnd(dateEnd: Calendar)
        fun setTimeEnd(timeEnd: Calendar)
        fun getDateStart(): Calendar
        fun getTimeStart(): Calendar
        fun getDateEnd(): Calendar
        fun getTimeEnd(): Calendar
        fun getFormattedString(calendar: Calendar, formatGiven: String): String
        fun getReservation(): Reservation
        fun completeReservationInfo(parkingSpace: Int, securityCode: Int)
        fun getReservationVerifyResult(): ReservationVerifiyResult
        fun getValidReservation(): ReservationVerifiyResult
        fun makeReservation(reservation: Reservation)
        fun releaseOldReservations()
    }

    interface ParkingSpaceReservationView {
        fun getButtonPickerStart(): Boolean
        fun showDatePickerDialog(dateListener: DatePickerDialog.OnDateSetListener)
        fun showTimePickerDialog(timeListener: TimePickerDialog.OnTimeSetListener)
        fun enableButtonEnd()
        fun showDateAndTimeStart(date: String, time: String)
        fun showDateAndTimeEnd(date: String, time: String)
        fun getParkingSpace(): String
        fun getSecurityCode(): String
        fun showMissingDateStart()
        fun showMissingTimeStart()
        fun showMissingDateEnd()
        fun showMissingTimeEnd()
        fun showMissingParkingSpace()
        fun showMissingSecurityCode()
        fun showReservationOverlapping()
        fun showReservationSuccess()
        fun showPastReservationsReleased(amountReservations: Int)
        fun showReservationList()
    }
}
