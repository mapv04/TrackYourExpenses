package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.*

class AccountActivity: AppCompatActivity(), DashboardFragment.Callbacks,
DashboardFragment.OnDeleteAccountListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navhost_account)
    }


    /**
     * Delete the selected account
     */
    override fun onAccountIdSelected(account: Accounts, fragment: DashboardFragment) {
        fragment.deleteAccount(account)
    }

    /**
     * Get the details for the selected account
     */
    override fun onAccountSelected(account: String, view: View?) {
        view?.let {
            //Pass account as argument
            val action = DashboardFragmentDirections.actionDashboardFragmentToDetailsActivity(account)
            it.findNavController().navigate(action)
        }
    }
}