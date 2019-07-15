package com.miguel.android.trackyourexpenses.ui.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.data.api.response.Account
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.DashboardFragment
import com.miguel.android.trackyourexpenses.ui.DashboardFragmentDirections

class DashboardActivity : AppCompatActivity(), DashboardFragment.Callbacks, DashboardFragment.onDeleteAccountListener {

    /**
     * Delete the selected account
     */
    override fun onAccountIdSelected(account: Accounts, accountListFragment: DashboardFragment) {
        accountListFragment.deleteAccount(account)
    }

    /**
     * Get the details for the selected account
     */
    override fun onAccountSelected(account: Account?, view: View?) {
        view?.let {
            //Pass account as argument
            val action = DashboardFragmentDirections.actionDashboardFragmentToAccountDetailsFragment(account)
            it.findNavController().navigate(action)
        }
    }


}

