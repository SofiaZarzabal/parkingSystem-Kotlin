package com.example.parkingsystemkotlin.mvp.contracts

import com.example.parkingsystemkotlin.listener.ConfigureParkingDialogListener

interface ConfigureParkingDialogContract {

    interface ConfigureParkingDialogPresenter {
        fun onButtonDialogFragmentConfirmPressed(listener: ConfigureParkingDialogListener)
        fun onButtonDialogFragmentCancelPressed()
    }

    interface ConfigureParkingDialogView {
        fun closeDialog()
        fun getParkingSpaces(): String
        fun showParkingSpaces(parkingSpaces: Int, listener: ConfigureParkingDialogListener)
        fun showEmptyToastInput()
    }
}
