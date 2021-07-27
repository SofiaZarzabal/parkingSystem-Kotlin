package com.example.parkingsystemkotlin.mvp.model

import android.icu.util.Calendar
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.util.DateUtils
import java.text.SimpleDateFormat
import java.util.Locale

class ParkingSpaceReservationModel : ParkingSpaceReservationContract.ParkingSpaceReservationModel {
    private var isDateStartButtonPressed = false
    private lateinit var dateStart: Calendar
    private lateinit var timeStart: Calendar
    private lateinit var dateEnd: Calendar
    private lateinit var timeEnd: Calendar

    override fun setDateStartButtonPressed(isDateStartButtonPressed: Boolean) {
        this.isDateStartButtonPressed = isDateStartButtonPressed
    }

    override fun convertToCalendar(sDate: String, formatGiven: String): Calendar {
        val format = SimpleDateFormat(formatGiven, Locale.getDefault())
        return DateUtils.convertToCalendar(sDate, format)
    }

    override fun getFormattedString(calendar: Calendar, formatGiven: String): String {
        val format = SimpleDateFormat(formatGiven, Locale.getDefault())
        return DateUtils.convertToString(calendar, format)
    }

    override fun getDateStartButtonPressed(): Boolean = isDateStartButtonPressed

    override fun setDateStart(dateStart: Calendar) {
        this.dateStart = dateStart
    }

    override fun setTimeStart(timeStart: Calendar) {
        this.timeStart = timeStart
    }

    override fun setDateEnd(dateEnd: Calendar) {
        this.dateEnd = dateEnd
    }

    override fun setTimeEnd(timeEnd: Calendar) {
        this.timeEnd = timeEnd
    }

    override fun getDateStart(): Calendar = dateStart

    override fun getTimeStart(): Calendar = timeStart

    override fun getDateEnd(): Calendar = dateEnd

    override fun getTimeEnd(): Calendar = timeEnd
}
