package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import androidx.fragment.app.DialogFragment
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.activity.ParkingSpaceReservationActivity
import com.example.parkingsystemkotlin.fragment.ConfigureParkingDialog
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView
import com.example.parkingsystemkotlin.util.Constants
import com.example.parkingsystemkotlin.util.toast

class ParkingView(activity: Activity) : ParkingContract.ParkingView, ActivityView(activity) {

    override fun showParkingAlertDialog() {
        val dialog: DialogFragment = ConfigureParkingDialog()
        dialog.show(fragmentManager, Constants.TAG_PARKING_VIEW)
    }

    override fun showParkingSpaces(parkingSpaces: Int) {
        context?.let {
            it.toast(it.getString(R.string.toast_main_activity_total_parking_lots, parkingSpaces))
        }
    }

    override fun showParkingSpaceReservation() {
        activity?.startActivity(context?.let { ParkingSpaceReservationActivity.getIntent(it) })
    }
}
