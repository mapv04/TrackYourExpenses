package com.miguel.android.trackyourexpenses.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.miguel.android.trackyourexpenses.R
import kotlinx.android.synthetic.main.fragment_account_details.view.*

class AccountDetailsFragment: Fragment() {

    private val args: AccountDetailsFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account_details, container, false)

        view.incomeTextView.setOnClickListener {
            val fragment = RecyclerViewIncomes(args.account.id)
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentAccountActivityContainer, fragment)
            transaction?.commit()

        }

        view.expenseTextView.setOnClickListener {
            /**
             * FRAGMENT MANAGER
             */
        }
        return view

    }


}