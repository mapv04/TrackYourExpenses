package com.miguel.android.trackyourexpenses.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.R
import kotlinx.android.synthetic.main.fragment_new_income.view.*

class NewIncomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_income, container, false)

        view.addButton.setOnClickListener{
            val fragment = NewMovementDialog()
            fragment.show(fragmentManager, null)
        }

        return view
    }
}