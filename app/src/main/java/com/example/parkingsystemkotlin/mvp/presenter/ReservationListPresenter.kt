package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.mvp.contract.ReservationListContract

class ReservationListPresenter(
    private val model: ReservationListContract.ReservationListModel,
    private val view: ReservationListContract.ReservationListView
) : ReservationListContract.ReservationListPresenter {

    init {
        listReservations()
    }

    override fun listReservations() {
        view.listReservations(model.getReservationList())
    }
}
