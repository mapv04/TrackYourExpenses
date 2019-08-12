package com.miguel.android.trackyourexpenses.ui.fragments

import android.graphics.Color
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = args.accountId

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account_details, container, false)

        view.incomeTextView.setTextColor(Color.RED)

        val fragment = RecyclerViewIncomes()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentAccountActivityContainer, fragment)
        transaction?.commit()

        view.incomeTextView.setOnClickListener {
            view.incomeTextView.setTextColor(Color.RED)
            view.expenseTextView.setTextColor(Color.BLACK)
            val fragment = RecyclerViewIncomes()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentAccountActivityContainer, fragment)
            transaction?.commit()

        }

        view.expenseTextView.setOnClickListener {
            view.expenseTextView.setTextColor(Color.RED)
            view.incomeTextView.setTextColor(Color.BLACK)
            val fragment = RecyclerViewExpenses()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentAccountActivityContainer, fragment)
            transaction?.commit()
        }
        return view

    }

    companion object{
        lateinit var account: String
    }


}