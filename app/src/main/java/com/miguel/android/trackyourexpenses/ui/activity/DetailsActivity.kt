package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.ui.fragments.NewMovementDialog
import com.miguel.android.trackyourexpenses.ui.fragments.NewMovementFragment
import com.miguel.android.trackyourexpenses.ui.OnMovementSelected
import com.miguel.android.trackyourexpenses.ui.fragments.AccountDetailsFragmentDirections

class DetailsActivity: AppCompatActivity(), NewMovementDialog.OnItemListListener,
    OnMovementSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.navhost_details)
        findNavController(R.id.myNavHostFragmentDetails).setGraph(R.navigation.navigation_details, intent.extras)
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
}