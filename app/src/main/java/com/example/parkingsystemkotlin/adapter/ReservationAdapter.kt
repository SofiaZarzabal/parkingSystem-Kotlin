package com.example.parkingsystemkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.databinding.ItemReservationBinding
import com.example.parkingsystemkotlin.entity.Reservation
import com.example.parkingsystemkotlin.util.Constants

class ReservationAdapter(private val reservations: List<Reservation>) : RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reservations[position], position)
    }

    override fun getItemCount(): Int = reservations.size

    class ViewHolder(private val binding: ItemReservationBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation, position: Int) {
            with(binding) {
                titleReservationList.text = context.getString(R.string.text_reservation_list, position + Constants.ONE)
                textReservationListDateStart.text = context.getString(
                    R.string.text_reservation_list_date_start,
                    reservation.getFormattedString(reservation.dateStart, Constants.FORMAT_DATE)
                )
                textReservationListTimeStart.text = context.getString(
                    R.string.text_reservation_list_time_start,
                    reservation.getFormattedString(reservation.timeStart, Constants.FORMAT_TIME)
                )
                textReservationListDateEnd.text = context.getString(
                    R.string.text_reservation_list_date_end,
                    reservation.getFormattedString(reservation.dateEnd, Constants.FORMAT_DATE)
                )
                textReservationListTimeEnd.text = context.getString(
                    R.string.text_reservation_list_time_end,
                    reservation.getFormattedString(reservation.timeEnd, Constants.FORMAT_TIME)
                )
                textReservationListParkingSpace.text =
                    context.getString(R.string.text_reservation_list_parking_space, reservation.parkingSpace.toString())
                textReservationListSecurityCode.text =
                    context.getString(R.string.text_reservation_list_security_code, reservation.securityCode.toString())
            }
        }
    }
}
