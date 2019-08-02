package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.databinding.ActivityFragmentBinding
import com.miguel.android.trackyourexpenses.ui.*

class MainActivity : AppCompatActivity(), DashboardFragment.Callbacks, DashboardFragment.OnDeleteAccountListener, NewMovementDialog.OnItemListListener {

    override fun onItemListCreated(item: ItemMov, fragment: NewMovementFragment) {
        fragment.itemList.add(item)
        fragment.newItem()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityFragmentBinding>(this, R.layout.activity_fragment)

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
            val action = DashboardFragmentDirections.actionDashboardFragmentToAccountDetailsFragment(account)
            it.findNavController().navigate(action)
        }
    }

}
