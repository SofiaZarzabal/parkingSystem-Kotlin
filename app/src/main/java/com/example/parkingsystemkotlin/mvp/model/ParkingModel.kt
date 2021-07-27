package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.util.Constants

class ParkingModel() : ParkingContract.ParkingModel {
    private var parkingSpaces: Int = Constants.ZERO

    override fun getParkingSpaces(): Int = parkingSpaces

    override fun setParkingSpaces(parkingSpaces: Int) {
        this.parkingSpaces = parkingSpaces
    }
}
