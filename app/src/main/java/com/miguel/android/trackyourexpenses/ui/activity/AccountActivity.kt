package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.*

class AccountActivity: AppCompatActivity(), DashboardFragment.Callbacks,
DashboardFragment.OnDeleteAccountListener,
NewMovementDialog.OnItemListListener,
    OnMovementSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navhost_account)
    }

    override fun onIncomeIdSelected(movementId: String?, view: View?) {
        view?.let{v->
            movementId?.let{
                val action = AccountDetailsFragmentDirections.actionAccountDetailsFragmentToMovementDetailsFragment(movementId, "income")
                v.findNavController().navigate(action)
            }
        }
    }

    override fun onExpenseIdSelected(movementId: String?, view: View?) {
        view?.let{v ->
            movementId?.let{
                val action = AccountDetailsFragmentDirections.actionAccountDetailsFragmentToMovementDetailsFragment(movementId, "expense")
                v.findNavController().navigate(action)
            }
        }
    }

    override fun onItemListCreated(item: ItemMov, fragment: NewMovementFragment) {
        fragment.itemList.add(item)
        fragment.newItem()
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