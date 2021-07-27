package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.databinding.ActivityReservationParkingSpaceBinding
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView

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
}
