package com.example.parkingsystemkotlin.mvp.view

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.databinding.DialogParkingConfigureBinding
import com.example.parkingsystemkotlin.listener.ConfigureParkingDialogListener
import com.example.parkingsystemkotlin.mvp.contracts.ConfigureParkingDialogContract
import com.example.parkingsystemkotlin.mvp.view.base.FragmentView
import com.example.parkingsystemkotlin.utils.toast

class ConfigureParkingDialogView(fragment: Fragment, private val binding: DialogParkingConfigureBinding) : FragmentView(fragment),
    ConfigureParkingDialogContract.ConfigureParkingDialogView {

    override fun closeDialog() {
        fragment?.let {
            val dialog: DialogFragment = it as DialogFragment
            dialog.dismiss()
        }
    }

    override fun getParkingSpaces(): String = binding.inputFragmentDialog.text.toString()

    override fun showParkingSpaces(parkingSpaces: Int, listener: ConfigureParkingDialogListener) {
        listener.onDialogPositiveClick(parkingSpaces)
    }

    override fun showEmptyToastInput() {
        context?.let {
            it.toast(it.getString(R.string.toast_configure_parking_dialog_empty_input))
        }
    }
}
