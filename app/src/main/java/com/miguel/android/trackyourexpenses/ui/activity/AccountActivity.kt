package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.PREF_KEEP_LOGIN
import com.miguel.android.trackyourexpenses.common.PREF_TOKEN
import com.miguel.android.trackyourexpenses.common.SharedPreferencesManager
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.*
import com.miguel.android.trackyourexpenses.ui.fragments.DashboardFragment
import com.miguel.android.trackyourexpenses.ui.fragments.DashboardFragmentDirections
import kotlinx.android.synthetic.main.navhost_account.*

class AccountActivity: AppCompatActivity(), DashboardFragment.Callbacks,
DashboardFragment.OnDeleteAccountListener{

    lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.navhost_account)
        navigationController = findNavController(R.id.myNavHostFragmentAccount)
        navigationController.setGraph(R.navigation.navigation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.log_out ->{
                navigationController.navigate(R.id.mainActivity)
                SharedPreferencesManager.setSomeBoolValue(PREF_KEEP_LOGIN, false)
                SharedPreferencesManager.setSomeStringValue(PREF_TOKEN, "")
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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