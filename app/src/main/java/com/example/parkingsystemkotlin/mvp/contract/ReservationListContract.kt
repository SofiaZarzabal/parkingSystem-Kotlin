package com.example.parkingsystemkotlin.mvp.contract

import com.example.parkingsystemkotlin.entity.Reservation

interface ReservationListContract {

    interface ReservationListPresenter {
        fun listReservations()
    }

    interface ReservationListModel {
        fun getReservationList(): List<Reservation>
    }

    interface ReservationListView {
        fun listReservations(reservations: List<Reservation>)
    }
}
