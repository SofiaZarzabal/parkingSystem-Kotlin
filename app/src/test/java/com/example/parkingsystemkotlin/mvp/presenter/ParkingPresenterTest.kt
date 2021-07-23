package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.mvp.contracts.ParkingContract
import com.example.parkingsystemkotlin.mvp.model.ParkingModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ParkingPresenterTest {

    private lateinit var presenter: ParkingContract.ParkingPresenter
    private lateinit var model: ParkingContract.ParkingModel
    private val view: ParkingContract.ParkingView = mock()

    @Before
    fun setup() {
        model = ParkingModel()
        presenter = ParkingPresenter(model, view)
    }

    @Test
    fun `on button select parking pressed, the view shows the dialog fragment`() {
        presenter.onButtonMainSelectParkingPressed()
        verify(view).showParkingAlertDialog()
    }

    @Test
    fun `on button parking spaces pressed, the view shows a Toast`() {
        presenter.onButtonDialogConfirmPressed(PARKING_SPACES)
        assertEquals(PARKING_SPACES, model.getParkingSpaces())
        verify(view).showParkingSpaces(model.getParkingSpaces())
    }

    companion object {
        private const val PARKING_SPACES = 5
    }
}
