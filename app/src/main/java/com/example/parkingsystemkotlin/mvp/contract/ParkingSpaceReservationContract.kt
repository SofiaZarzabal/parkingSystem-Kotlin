package com.example.parkingsystemkotlin.mvp.contract

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar

interface ParkingSpaceReservationContract {

    interface ParkingSpaceReservationPresenter {
        fun onButtonParkingSpaceReservationPickerPressed(listener: DatePickerDialog.OnDateSetListener)
        fun onDateSetPressed(year: Int, month: Int, dayOfMonth: Int, timeListener: TimePickerDialog.OnTimeSetListener)
        fun onTimeSetPressed(hourOfDay: Int, minute: Int)
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
    }

    interface ParkingSpaceReservationView {
        fun getButtonPickerStart(): Boolean
        fun showDatePickerDialog(dateListener: DatePickerDialog.OnDateSetListener)
        fun showTimePickerDialog(timeListener: TimePickerDialog.OnTimeSetListener)
        fun enableButtonEnd()
        fun showDateAndTimeStart(date: String, time: String)
        fun showDateAndTimeEnd(date: String, time: String)
    }
}