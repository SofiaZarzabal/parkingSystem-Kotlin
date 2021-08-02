package com.example.parkingsystemkotlin.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.util.Constants
import com.example.parkingsystemkotlin.util.ReservationVerifiyResult

class ParkingSpaceReservationPresenter(
    private val model: ParkingSpaceReservationContract.ParkingSpaceReservationModel,
    private val view: ParkingSpaceReservationContract.ParkingSpaceReservationView
) : ParkingSpaceReservationContract.ParkingSpaceReservationPresenter {

    override fun onButtonParkingSpaceReservationPickerPressed(listener: DatePickerDialog.OnDateSetListener) {
        model.setDateStartButtonPressed(view.getButtonPickerStart())
        view.showDatePickerDialog(listener)
    }

    override fun onDateSetPressed(year: Int, month: Int, dayOfMonth: Int, timeListener: TimePickerDialog.OnTimeSetListener) {
        val sDate = "$dayOfMonth${Constants.SLASH}${month + Constants.ONE}${Constants.SLASH}$year"
        val date = model.convertToCalendar(sDate, Constants.FORMAT_DATE)
        if (model.getDateStartButtonPressed()) {
            model.setDateStart(date)
        } else {
            model.setDateEnd(date)
        }
        view.showTimePickerDialog(timeListener)
    }

    override fun onTimeSetPressed(hourOfDay: Int, minute: Int) {
        val sTime = "$hourOfDay${Constants.TWO_POINTS}$minute"
        val time = model.convertToCalendar(sTime, Constants.FORMAT_TIME)
        if (model.getDateStartButtonPressed()) {
            model.setTimeStart(time)
            view.enableButtonEnd()
            view.showDateAndTimeStart(
                model.getFormattedString(model.getDateStart(), Constants.FORMAT_DATE),
                model.getFormattedString(model.getTimeStart(), Constants.FORMAT_TIME)
            )
        } else {
            model.setTimeEnd(time)
            view.showDateAndTimeEnd(
                model.getFormattedString(model.getDateEnd(), Constants.FORMAT_DATE),
                model.getFormattedString(model.getTimeEnd(), Constants.FORMAT_TIME)
            )
        }
    }

    override fun onButtonParkingSpaceReservationSavePressed() {
        val parkingSpace = if (view.getParkingSpace().isNotEmpty()) view.getParkingSpace().toInt() else Constants.MINUS_ONE
        val securityCode = if (view.getSecurityCode().isNotEmpty()) view.getSecurityCode().toInt() else Constants.MINUS_ONE
        model.completeReservationInfo(parkingSpace, securityCode)
        when (model.getReservationVerifyResult()) {
            ReservationVerifiyResult.MISSING_DATE_START -> view.showMissingDateStart()
            ReservationVerifiyResult.MISSING_TIME_START -> view.showMissingTimeStart()
            ReservationVerifiyResult.MISSING_DATE_END -> view.showMissingDateEnd()
            ReservationVerifiyResult.MISSING_TIME_END -> view.showMissingTimeEnd()
            ReservationVerifiyResult.MISSING_PARKING_SPACE -> view.showMissingParkingSpace()
            ReservationVerifiyResult.MISSING_SECURITY_CODE -> view.showMissingSecurityCode()
            ReservationVerifiyResult.FIELDS_COMPLETE -> {
                val canBeReserved = model.getValidReservation()
                if (canBeReserved == ReservationVerifiyResult.SUCCESS) {
                    model.makeReservation(model.getReservation())
                    view.showReservationSuccess()
                } else {
                    view.showReservationOverlapping()
                }
            }
        }
    }
    override fun clearOldReservations() {
        model.releaseOldReservations()
    }
}
