package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.databinding.ActivityDashboardFragmentBinding
import com.miguel.android.trackyourexpenses.ui.DashboardFragment
import com.miguel.android.trackyourexpenses.ui.DashboardFragmentDirections

class DashboardActivity : AppCompatActivity(), DashboardFragment.Callbacks, DashboardFragment.onDeleteAccountListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityDashboardFragmentBinding>(this, R.layout.activity_dashboard_fragment)
    }

    /**
     * Delete the selected account
     */
    override fun onAccountIdSelected(account: Accounts, accountListFragment: DashboardFragment) {
        accountListFragment.deleteAccount(account)
    }

    /**
     * Get the details for the selected account
     */
    override fun onAccountSelected(account: Accounts?, view: View?) {
        view?.let {
            //Pass account as argument
            val action = DashboardFragmentDirections.actionDashboardFragmentToAccountDetailsFragment(account!!)
            it.findNavController().navigate(action)
        }
    }


}

