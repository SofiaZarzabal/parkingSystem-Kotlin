package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.mvp.contract.ParkingContract

class ParkingPresenter(private val model: ParkingContract.ParkingModel, private val view: ParkingContract.ParkingView) :
    ParkingContract.ParkingPresenter {

    override fun onButtonMainSelectParkingPressed() {
        view.showParkingAlertDialog()
    }

    override fun onButtonDialogConfirmPressed(parkingSpaces: Int) {
        model.setParkingSpaces(parkingSpaces)
        view.showParkingSpaces(model.getParkingSpaces())
    }

    override fun onButtonMainBookParkingLotPressed() {
        view.showParkingSpaceReservation()
    }
}
