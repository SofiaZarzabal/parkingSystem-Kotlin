package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.database.ParkingSpaceReservationDB
import com.example.parkingsystemkotlin.mvp.contract.ReservationListContract
import com.example.parkingsystemkotlin.mvp.model.ReservationListModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test


class ReservationListPresenterTest {
    private lateinit var presenter: ReservationListContract.ReservationListPresenter
    private lateinit var model: ReservationListContract.ReservationListModel
    private val view: ReservationListContract.ReservationListView = mock()

    @Before
    fun setup() {
        model = ReservationListModel(ParkingSpaceReservationDB)
        presenter = ReservationListPresenter(model, view)
    }

    @Test
    fun `on activity creates, saved reservations are displayed`() {
        presenter.listReservations()

        verify(view).listReservations(model.getReservationList())
    }

}
