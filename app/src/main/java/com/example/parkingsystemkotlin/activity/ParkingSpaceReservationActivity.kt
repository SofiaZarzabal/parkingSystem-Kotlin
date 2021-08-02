package com.example.parkingsystemkotlin.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystemkotlin.database.ParkingSpaceReservationDB
import com.example.parkingsystemkotlin.databinding.ActivityReservationParkingSpaceBinding
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.mvp.model.ParkingSpaceReservationModel
import com.example.parkingsystemkotlin.mvp.presenter.ParkingSpaceReservationPresenter
import com.example.parkingsystemkotlin.mvp.view.ParkingSpaceReservationView

class ParkingSpaceReservationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityReservationParkingSpaceBinding
    private lateinit var presenter: ParkingSpaceReservationContract.ParkingSpaceReservationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationParkingSpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ParkingSpaceReservationPresenter(
            ParkingSpaceReservationModel(ParkingSpaceReservationDB),
            ParkingSpaceReservationView(this, binding)
        )
        clearPastReservations()
        setListeners()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        presenter.onDateSetPressed(year, month, dayOfMonth, this)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        presenter.onTimeSetPressed(hourOfDay, minute)
    }

    private fun clearPastReservations() {
        presenter.clearOldReservations()
    }

    private fun setListeners() {
        with(binding) {
            buttonParkingSpaceReservationPickerBegin.setOnClickListener { presenter.onButtonParkingSpaceReservationPickerPressed(this@ParkingSpaceReservationActivity) }
            buttonParkingSpaceReservationPickerEnd.setOnClickListener { presenter.onButtonParkingSpaceReservationPickerPressed(this@ParkingSpaceReservationActivity) }
            buttonParkingSpaceReservationSave.setOnClickListener { presenter.onButtonParkingSpaceReservationSavePressed() }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, ParkingSpaceReservationActivity::class.java)
    }
}
