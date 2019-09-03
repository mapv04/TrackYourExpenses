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
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.ui.fragments.NewMovementDialog
import com.miguel.android.trackyourexpenses.ui.fragments.NewMovementFragment
import com.miguel.android.trackyourexpenses.ui.OnMovementSelected
import com.miguel.android.trackyourexpenses.ui.fragments.AccountDetailsFragmentDirections
import kotlinx.android.synthetic.main.navhost_details.*

class DetailsActivity: AppCompatActivity(), NewMovementDialog.OnItemListListener,
    OnMovementSelected {

    lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.navhost_details)
        navigationController = findNavController(R.id.myNavHostFragmentDetails)
        navigationController.setGraph(R.navigation.navigation_details, intent.extras)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.log_out ->{
                navigationController.navigate(R.id.mainActivityDetails)
                SharedPreferencesManager.setSomeBoolValue(PREF_KEEP_LOGIN, false)
                SharedPreferencesManager.setSomeStringValue(PREF_TOKEN, "")
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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