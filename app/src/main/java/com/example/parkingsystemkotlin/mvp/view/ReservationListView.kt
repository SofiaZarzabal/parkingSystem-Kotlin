package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingsystemkotlin.adapter.ReservationAdapter
import com.example.parkingsystemkotlin.databinding.ActivityListReservationBinding
import com.example.parkingsystemkotlin.entity.Reservation
import com.example.parkingsystemkotlin.mvp.contract.ReservationListContract
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView

class ReservationListView(activity: Activity, private val binding: ActivityListReservationBinding) : ActivityView(activity),
    ReservationListContract.ReservationListView {

    override fun listReservations(reservations: List<Reservation>) {
        with(binding.recyclerViewReservationList) {
            adapter = ReservationAdapter(reservations)
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
