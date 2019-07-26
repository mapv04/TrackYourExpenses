package com.miguel.android.trackyourexpenses.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.miguel.android.trackyourexpenses.R

class NewMovementDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var builder: AlertDialog.Builder
        activity?.let {
            builder = AlertDialog.Builder(it)
            val inflater = it.layoutInflater
            builder.setView(inflater.inflate(R.layout.fragment_new_item, null))
        }

        return builder.create()
    }
}