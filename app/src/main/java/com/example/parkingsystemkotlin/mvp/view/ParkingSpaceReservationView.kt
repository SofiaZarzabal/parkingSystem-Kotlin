package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.activity.ReservationListActivity
import com.example.parkingsystemkotlin.databinding.ActivityReservationParkingSpaceBinding
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView
import com.example.parkingsystemkotlin.util.toast

class ParkingSpaceReservationView(activity: Activity, private val binding: ActivityReservationParkingSpaceBinding) :
    ParkingSpaceReservationContract.ParkingSpaceReservationView, ActivityView(activity) {

    override fun getButtonPickerStart(): Boolean = binding.buttonParkingSpaceReservationPickerBegin.isPressed

    override fun showDatePickerDialog(dateListener: DatePickerDialog.OnDateSetListener) {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        context?.let {
            val datePicker = DatePickerDialog(it, dateListener, year, month, day)
            datePicker.show()
        }
    }

    override fun showTimePickerDialog(timeListener: TimePickerDialog.OnTimeSetListener) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        context?.let {
            val timePicker = TimePickerDialog(it, timeListener, hour, minute, false)
            timePicker.show()
        }
    }

    override fun enableButtonEnd() {
        binding.buttonParkingSpaceReservationPickerEnd.isEnabled = true
    }

    override fun showDateAndTimeStart(date: String, time: String) {
        context?.let {
            binding.textParkingSpaceReservationPickerBegin.text = it.getString(R.string.text_parking_space_reservation_picker, date, time)
        }
    }

    override fun showDateAndTimeEnd(date: String, time: String) {
        context?.let {
            binding.textParkingSpaceReservationPickerEnd.text = it.getString(R.string.text_parking_space_reservation_picker, date, time)
        }
    }

    override fun getParkingSpace(): String = binding.inputParkingSpaceReservationPlace.text.toString()

    override fun getSecurityCode(): String = binding.inputParkingSpaceReservationCode.text.toString()

    override fun showMissingDateStart() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_missing_date_start))
        }
    }

    override fun showMissingTimeStart() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_missing_time_start))
        }
    }

    override fun showMissingDateEnd() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_missing_date_end))
        }
    }

    override fun showMissingTimeEnd() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_missing_time_end))
        }
    }

    override fun showMissingParkingSpace() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_missing_place))
        }
    }

    override fun showMissingSecurityCode() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_missing_security_code))
        }
    }

    override fun showReservationOverlapping() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_reservation_overlapping))
        }
    }

    override fun showReservationSuccess() {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_save))
        }
        activity?.finish()
    }

    override fun showPastReservationsReleased(amountReservations: Int) {
        context?.let {
            it.toast(it.getString(R.string.toast_parking_space_reservation_amount_reserves_released, amountReservations))
        }
    }

    override fun showReservationList() {
        activity?.startActivity(context?.let { ReservationListActivity.getIntent(it) })
    }
}
