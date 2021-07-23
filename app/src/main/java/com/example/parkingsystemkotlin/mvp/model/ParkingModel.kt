package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contracts.ParkingContract

class ParkingModel() : ParkingContract.ParkingModel {
    private var parkingSpaces: Int = 0

    override fun getParkingSpaces(): Int = parkingSpaces

    override fun setParkingSpaces(parkingSpaces: Int) {
        this.parkingSpaces = parkingSpaces
    }
}
