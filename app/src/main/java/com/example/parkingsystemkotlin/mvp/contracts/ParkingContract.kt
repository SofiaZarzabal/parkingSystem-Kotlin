package com.example.parkingsystemkotlin.mvp.contracts

interface ParkingContract {

    interface ParkingPresenter {
        fun onButtonMainSelectParkingPressed()
        fun onButtonDialogConfirmPressed(parkingSpaces: Int)
    }

    interface ParkingModel {
        fun getParkingSpaces(): Int
        fun setParkingSpaces(parkingSpaces: Int)
    }

    interface ParkingView {
        fun showParkingAlertDialog()
        fun showParkingSpaces(parkingSpaces: Int)
    }
}
