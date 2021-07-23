package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ConfigureParkingDialogListener
import com.example.parkingsystemkotlin.mvp.contracts.ConfigureParkingDialogContract

class ConfigureParkingDialogPresenter(private val view: ConfigureParkingDialogContract.ConfigureParkingDialogView) :
    ConfigureParkingDialogContract.ConfigureParkingDialogPresenter {

    override fun onButtonDialogFragmentConfirmPressed(listener: ConfigureParkingDialogListener) {
        val parkingSpaces: String = view.getParkingSpaces()
        if (parkingSpaces.isNotEmpty()) {
            val intParkingSpaces = parkingSpaces.toInt()
            view.closeDialog()
            view.showParkingSpaces(intParkingSpaces, listener)
        } else {
            view.showEmptyToastInput()
        }
    }

    override fun onButtonDialogFragmentCancelPressed() {
        view.closeDialog()
    }
}
