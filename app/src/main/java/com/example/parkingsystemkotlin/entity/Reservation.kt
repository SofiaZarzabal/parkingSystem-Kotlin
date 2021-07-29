package com.example.parkingsystemkotlin.entity

import com.example.parkingsystemkotlin.util.Constants
import com.example.parkingsystemkotlin.util.DateUtils
import com.example.parkingsystemkotlin.util.ReservationVerifiyResult
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Reservation() {
    lateinit var dateStart: Calendar
    lateinit var timeStart: Calendar
    lateinit var dateEnd: Calendar
    lateinit var timeEnd: Calendar
    var parkingSpace: Int = Constants.MINUS_ONE
    var securityCode: Int = Constants.MINUS_ONE

    fun getReservationVerifyResult(): ReservationVerifiyResult =
        when {
            !::dateStart.isInitialized -> ReservationVerifiyResult.MISSING_DATE_START
            !::timeStart.isInitialized -> ReservationVerifiyResult.MISSING_TIME_START
            !::dateEnd.isInitialized -> ReservationVerifiyResult.MISSING_DATE_END
            !::timeEnd.isInitialized -> ReservationVerifiyResult.MISSING_TIME_END
            parkingSpace == Constants.MINUS_ONE -> ReservationVerifiyResult.MISSING_PARKING_SPACE
            securityCode == Constants.MINUS_ONE -> ReservationVerifiyResult.MISSING_SECURITY_CODE
            else -> ReservationVerifiyResult.FIELDS_COMPLETE
        }

    fun getFormattedString(calendar: Calendar, formatGiven: String): String {
        val format = SimpleDateFormat(formatGiven, Locale.getDefault())
        return DateUtils.convertToString(calendar, format)
    }

    fun getDateAndTimeStart(): Calendar = getCalendarDateAndTime(
        dateStart.get(Calendar.YEAR),
        dateStart.get(Calendar.MONTH),
        dateStart.get(Calendar.DAY_OF_MONTH),
        timeStart.get(Calendar.HOUR_OF_DAY),
        timeStart.get(Calendar.MINUTE)
    )

    fun getDateAndTimeEnd(): Calendar = getCalendarDateAndTime(
        dateEnd.get(Calendar.YEAR),
        dateEnd.get(Calendar.MONTH),
        dateEnd.get(Calendar.DAY_OF_MONTH),
        timeEnd.get(Calendar.HOUR_OF_DAY),
        timeEnd.get(Calendar.MINUTE)
    )

    private fun getCalendarDateAndTime(year: Int, month: Int, day: Int, hour: Int, minute: Int): Calendar {
        val calendar = Calendar.getInstance()
        with(calendar) {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, minute)
        }
        return calendar
    }
}
