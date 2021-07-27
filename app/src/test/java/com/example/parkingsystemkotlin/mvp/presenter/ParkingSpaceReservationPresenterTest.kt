package com.example.parkingsystemkotlin.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.mvp.model.ParkingSpaceReservationModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class ParkingSpaceReservationPresenterTest {
    private lateinit var presenter: ParkingSpaceReservationContract.ParkingSpaceReservationPresenter
    private lateinit var model: ParkingSpaceReservationContract.ParkingSpaceReservationModel
    private val view: ParkingSpaceReservationContract.ParkingSpaceReservationView = mock()
    private val datePickerListener: DatePickerDialog.OnDateSetListener = mock()
    private val timePickerListener: TimePickerDialog.OnTimeSetListener = mock()
    private val mockModel: ParkingSpaceReservationContract.ParkingSpaceReservationModel = mock()

    @Before
    fun setup() {
        model = ParkingSpaceReservationModel()
        presenter = ParkingSpaceReservationPresenter(model, view)
    }

    private fun createTimeCalendar(hour: Int, minute: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return calendar;
    }

    private fun createDateCalendar(year: Int, month: Int, day: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar
    }

    @Test
    fun `on first button picker pressed, the view return true when verify if the first button is pressed, and show the Date picker`() {
        whenever(view.getButtonPickerStart()).thenReturn(true)

        presenter.onButtonParkingSpaceReservationPickerPressed(datePickerListener)

        verify(view).showDatePickerDialog(datePickerListener)
    }

    @Test
    fun `on second button picker pressed, the view return false when verify if the first button is pressed, and show the Date picker`() {
        whenever(view.getButtonPickerStart()).thenReturn(false)

        presenter.onButtonParkingSpaceReservationPickerPressed(datePickerListener)

        verify(view).showDatePickerDialog(datePickerListener)
    }

    @Test
    fun `on date start set pressed, the model converts date string to calendar, set the date start, and the view shows the time picker`() {
        val presenterWithMockModel: ParkingSpaceReservationContract.ParkingSpaceReservationPresenter = ParkingSpaceReservationPresenter(
            mockModel,
            view
        )
        val dateCalendar = createDateCalendar(YEAR, MONTH_PLUS_ONE, DAY_START)

        whenever(mockModel.convertToCalendar(DATE_START_STRING, FORMAT_DATE)).thenReturn(dateCalendar)

        whenever(mockModel.getDateStartButtonPressed()).thenReturn(true)

        presenterWithMockModel.onDateSetPressed(YEAR, MONTH, DAY_START, timePickerListener)

        verify(mockModel).setDateStart(dateCalendar)
        verify(view).showTimePickerDialog(timePickerListener)
    }

    @Test
    fun `on date end set pressed, the model converts date string to calendar, set the date end, and the view shows the time picker`() {
        val presenterWithMockModel: ParkingSpaceReservationContract.ParkingSpaceReservationPresenter = ParkingSpaceReservationPresenter(
            mockModel,
            view
        )
        val dateCalendar = createDateCalendar(YEAR, MONTH_PLUS_ONE, DAY_END)

        whenever(mockModel.convertToCalendar(DATE_END_STRING, FORMAT_DATE)).thenReturn(dateCalendar)

        whenever(mockModel.getDateStartButtonPressed()).thenReturn(false)

        presenterWithMockModel.onDateSetPressed(YEAR, MONTH, DAY_END, timePickerListener)

        verify(mockModel).setDateEnd(dateCalendar)
        verify(view).showTimePickerDialog(timePickerListener)
    }

    @Test
    fun `on time start set pressed, the model converts date and time calendar to string, set the time start, and the view enable the end button and shows the date and time start`() {
        val presenterWithMockModel: ParkingSpaceReservationContract.ParkingSpaceReservationPresenter = ParkingSpaceReservationPresenter(
            mockModel,
            view
        )
        val dateCalendar = createDateCalendar(YEAR, MONTH_PLUS_ONE, DAY_START)
        val timeCalendar = createTimeCalendar(HOUR_START, MINUTE_START)
        whenever(mockModel.convertToCalendar(TIME_START_STRING, FORMAT_TIME)).thenReturn(timeCalendar)
        whenever(mockModel.getDateStartButtonPressed()).thenReturn(true)
        whenever(mockModel.getDateStart()).thenReturn(dateCalendar)
        whenever(mockModel.getTimeStart()).thenReturn(timeCalendar)
        whenever(mockModel.getFormattedString(mockModel.getDateStart(), FORMAT_DATE)).thenReturn(DATE_START_STRING)
        whenever(mockModel.getFormattedString(mockModel.getTimeStart(), FORMAT_TIME)).thenReturn(TIME_START_STRING)

        presenterWithMockModel.onTimeSetPressed(HOUR_START, MINUTE_START)

        verify(mockModel).setTimeStart(timeCalendar)
        verify(view).enableButtonEnd()
        verify(view).showDateAndTimeStart(DATE_START_STRING, TIME_START_STRING)
    }

    @Test
    fun `on time end set pressed, the model converts date and time calendar to string, set the time end, and the view shows the date and time end`() {
        val presenterWithMockModel: ParkingSpaceReservationContract.ParkingSpaceReservationPresenter = ParkingSpaceReservationPresenter(
            mockModel,
            view
        )
        val dateCalendar = createDateCalendar(YEAR, MONTH_PLUS_ONE, DAY_END)
        val timeCalendar = createTimeCalendar(HOUR_END, MINUTE_END)
        whenever(mockModel.convertToCalendar(TIME_END_STRING, FORMAT_TIME)).thenReturn(timeCalendar)
        whenever(mockModel.getDateStartButtonPressed()).thenReturn(false)
        whenever(mockModel.getDateEnd()).thenReturn(dateCalendar)
        whenever(mockModel.getTimeEnd()).thenReturn(timeCalendar)
        whenever(mockModel.getFormattedString(mockModel.getDateEnd(), FORMAT_DATE)).thenReturn(DATE_END_STRING)
        whenever(mockModel.getFormattedString(mockModel.getTimeEnd(), FORMAT_TIME)).thenReturn(TIME_END_STRING)

        presenterWithMockModel.onTimeSetPressed(HOUR_END, MINUTE_END)

        verify(mockModel).setTimeEnd(timeCalendar)
        verify(view).showDateAndTimeEnd(DATE_END_STRING, TIME_END_STRING)
    }

    companion object {
        private const val YEAR = 2021
        private const val MONTH = 8
        private const val MONTH_PLUS_ONE = 9
        private const val DAY_START = 5
        private const val DAY_END = 9
        private const val DATE_START_STRING = "5/9/2021"
        private const val DATE_END_STRING = "9/9/2021"
        private const val FORMAT_DATE = "dd/MM/yy"
        private const val HOUR_START = 6
        private const val MINUTE_START = 30
        private const val TIME_START_STRING = "6:30"
        private const val HOUR_END = 7
        private const val MINUTE_END = 45
        private const val TIME_END_STRING = "7:45"
        private const val FORMAT_TIME = "HH:mm"
    }
}
