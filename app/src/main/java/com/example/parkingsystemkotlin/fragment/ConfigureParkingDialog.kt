package com.example.parkingsystemkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.parkingsystemkotlin.databinding.DialogParkingConfigureBinding
import com.example.parkingsystemkotlin.listener.ConfigureParkingDialogListener
import com.example.parkingsystemkotlin.mvp.contract.ConfigureParkingDialogContract
import com.example.parkingsystemkotlin.mvp.presenter.ConfigureParkingDialogPresenter
import com.example.parkingsystemkotlin.mvp.view.ConfigureParkingDialogView

class ConfigureParkingDialog : DialogFragment() {

    private lateinit var binding: DialogParkingConfigureBinding
    private lateinit var presenter: ConfigureParkingDialogContract.ConfigureParkingDialogPresenter
    private lateinit var listener: ConfigureParkingDialogListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogParkingConfigureBinding.inflate(layoutInflater)
        setListeners()
        listener = activity as ConfigureParkingDialogListener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ConfigureParkingDialogPresenter(ConfigureParkingDialogView(this, binding))
    }

    private fun setListeners() {
        binding.buttonDialogFragmentConfirm.setOnClickListener { presenter.onButtonDialogFragmentConfirmPressed(listener) }
        binding.buttonDialogFragmentCancel.setOnClickListener { presenter.onButtonDialogFragmentCancelPressed() }
    }
}
