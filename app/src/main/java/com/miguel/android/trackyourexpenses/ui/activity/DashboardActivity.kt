package com.miguel.android.trackyourexpenses.ui.activity

import android.content.Intent
import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.DashboardFragment

class DashboardActivity : SingleFragmentActivity(), DashboardFragment.Callbacks, DashboardFragment.onDeleteAccountListener {
    /**
     * Delete the selected account
     */
    override fun onAccountIdSelected(account: Accounts) {
        val accountListFragment =  supportFragmentManager.findFragmentById(R.id.fragment_container) as DashboardFragment
        accountListFragment.deleteAccount(account)
    }

    /**
     * Get the details for the selected account
     */
    override fun onAccountSelected(account: Accounts?) {
        val intent = Intent(this, AccountDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun createFragment(): Fragment = DashboardFragment.newInstance()

}

