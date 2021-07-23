package com.example.parkingsystemkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystemkotlin.databinding.ActivityMainBinding
import com.example.parkingsystemkotlin.listener.ConfigureParkingDialogListener
import com.example.parkingsystemkotlin.mvp.contracts.ParkingContract
import com.example.parkingsystemkotlin.mvp.model.ParkingModel
import com.example.parkingsystemkotlin.mvp.presenter.ParkingPresenter
import com.example.parkingsystemkotlin.mvp.view.ParkingView

class MainActivity : AppCompatActivity(), ConfigureParkingDialogListener {

    private lateinit var presenter: ParkingContract.ParkingPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ParkingPresenter(ParkingModel(), ParkingView(this))
        setListener()
    }

    private fun setListener() {
        binding.buttonMainSelectParking.setOnClickListener { presenter.onButtonMainSelectParkingPressed() }
    }

    override fun onDialogPositiveClick(parkingSpaces: Int) {
        presenter.onButtonDialogConfirmPressed(parkingSpaces)
    }
}
