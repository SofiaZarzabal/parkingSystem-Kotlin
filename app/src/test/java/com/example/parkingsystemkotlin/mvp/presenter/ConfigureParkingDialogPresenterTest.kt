package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ConfigureParkingDialogListener
import com.example.parkingsystemkotlin.mvp.contract.ConfigureParkingDialogContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class ConfigureParkingDialogPresenterTest {
    private lateinit var presenter: ConfigureParkingDialogContract.ConfigureParkingDialogPresenter
    private val view: ConfigureParkingDialogContract.ConfigureParkingDialogView = mock()
    private val listener: ConfigureParkingDialogListener = mock()

    @Before
    fun setup() {
        presenter = ConfigureParkingDialogPresenter(view)
    }

    @Test
    fun `on button confirm pressed, and the parkingSpaces are not empty, the fragment dialog close and show the parking spaces`() {
        whenever(view.getParkingSpaces()).thenReturn(STRING_PARKING_SPACES)

        presenter.onButtonDialogFragmentConfirmPressed(listener)

        verify(view).closeDialog()
        verify(view).showParkingSpaces(INT_PARKING_SPACES, listener)
    }

    @Test
    fun `on button confirm pressed, and the parkingSpaces are empty, the fragment shows an error toast`() {
        whenever(view.getParkingSpaces()).thenReturn(EMPTY_PARKING_SPACES)

        presenter.onButtonDialogFragmentConfirmPressed(listener)

        verify(view).showEmptyToastInput()
    }

    @Test
    fun `on button cancel pressed, the fragment dialog close`() {
        presenter.onButtonDialogFragmentCancelPressed()

        verify(view).closeDialog()
    }

    companion object {
        private const val EMPTY_PARKING_SPACES = ""
        private const val STRING_PARKING_SPACES = "5"
        private const val INT_PARKING_SPACES = 5
    }
}
