package com.example.parkingsystemkotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystemkotlin.database.ParkingSpaceReservationDB
import com.example.parkingsystemkotlin.databinding.ActivityListReservationBinding
import com.example.parkingsystemkotlin.mvp.contract.ReservationListContract
import com.example.parkingsystemkotlin.mvp.model.ReservationListModel
import com.example.parkingsystemkotlin.mvp.presenter.ReservationListPresenter
import com.example.parkingsystemkotlin.mvp.view.ReservationListView

class ReservationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListReservationBinding
    private lateinit var presenter: ReservationListContract.ReservationListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ReservationListPresenter(ReservationListModel(ParkingSpaceReservationDB), ReservationListView(this, binding))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, ReservationListActivity::class.java)
    }
}
