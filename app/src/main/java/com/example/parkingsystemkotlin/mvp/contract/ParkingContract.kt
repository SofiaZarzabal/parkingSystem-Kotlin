package com.example.parkingsystemkotlin.mvp.contract

interface ParkingContract {

    interface ParkingPresenter {
        fun onButtonMainSelectParkingPressed()
        fun onButtonDialogConfirmPressed(parkingSpaces: Int)
        fun onButtonMainBookParkingLotPressed()
    }

    interface ParkingModel {
        fun getParkingSpaces(): Int
        fun setParkingSpaces(parkingSpaces: Int)
    }

    interface ParkingView {
        fun showParkingAlertDialog()
        fun showParkingSpaces(parkingSpaces: Int)
        fun showParkingSpaceReservation()
    }
}
