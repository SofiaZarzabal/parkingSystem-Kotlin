package com.example.parkingsystemkotlin.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.entity.Reservation
import com.example.parkingsystemkotlin.mvp.contract.ParkingSpaceReservationContract
import com.example.parkingsystemkotlin.mvp.model.ParkingSpaceReservationModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import java.util.Calendar
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

    private fun createReservation(): Reservation {
        val reservation = Reservation()
        reservation.dateStart = createDateCalendar(YEAR, MONTH, DAY_START_OVERLAP)
        reservation.timeStart = createTimeCalendar(HOUR_START, MINUTE_START)
        reservation.dateEnd = createDateCalendar(YEAR, MONTH, DAY_END)
        reservation.timeEnd = createTimeCalendar(HOUR_END, MINUTE_END)
        reservation.securityCode = SECURITY_CODE_INT
        reservation.parkingSpace = PARKING_SPACE_INT
        return reservation
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

    @Test
    fun `on save reservation button pressed, the missing date start message shows`() {
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showMissingDateStart()
    }

    @Test
    fun `on save reservation button pressed, the missing time start message shows`() {
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showMissingTimeStart()
    }

    @Test
    fun `on save reservation button pressed, the missing date end message shows`() {
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START))
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showMissingDateEnd()
    }

    @Test
    fun `on save reservation button pressed, the missing time end message shows`() {
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START))
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START))
        model.setDateEnd(createDateCalendar(YEAR, MONTH, DAY_END))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showMissingTimeEnd()
    }

    @Test
    fun `on save reservation button pressed, the missing parking parking space message shows`() {
        whenever(view.getParkingSpace()).thenReturn(EMPTY_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START))
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START))
        model.setDateEnd(createDateCalendar(YEAR, MONTH, DAY_END))
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showMissingParkingSpace()
    }

    @Test
    fun `on save reservation button pressed, the missing security code message shows`() {
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(EMPTY_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START))
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START))
        model.setDateEnd(createDateCalendar(YEAR, MONTH, DAY_END))
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showMissingSecurityCode()
    }

    @Test
    fun `on save reservation button pressed, the reservation overlapping message shows`() {
        model.makeReservation(createReservation())
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START))
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START))
        model.setDateEnd(createDateCalendar(YEAR, MONTH, DAY_END))
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showReservationOverlapping()
    }

    @Test
    fun `on save reservation button pressed, the reservation save successfully message shows`() {
        whenever(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING)
        whenever(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING)
        model.setDateStart(createDateCalendar(YEAR, MONTH, DAY_START_SUCCESS))
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START))
        model.setDateEnd(createDateCalendar(YEAR, MONTH, DAY_END))
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END))

        presenter.onButtonParkingSpaceReservationSavePressed()

        verify(view).showReservationSuccess()
    }

    companion object {
        private const val YEAR = 2021
        private const val MONTH = 8
        private const val MONTH_PLUS_ONE = 9
        private const val DAY_START = 5
        private const val DAY_START_OVERLAP = 7
        private const val DAY_START_SUCCESS = 9
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
        private const val PARKING_SPACE_STRING = "2"
        private const val SECURITY_CODE_STRING = "525"
        private const val PARKING_SPACE_INT = 2
        private const val SECURITY_CODE_INT = 873
        private const val EMPTY_STRING = ""

    }
}
