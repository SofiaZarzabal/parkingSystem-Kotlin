package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.database.ParkingSpaceReservationDB
import com.example.parkingsystemkotlin.entity.Reservation
import com.example.parkingsystemkotlin.mvp.contract.ReservationListContract

class ReservationListModel(private val database: ParkingSpaceReservationDB) : ReservationListContract.ReservationListModel {

    override fun getReservationList(): List<Reservation> = database.getAllReservations()

}
