package com.miguel.android.trackyourexpenses.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository
import kotlinx.android.synthetic.main.fragment_new_movement.view.*

/**
 * This fragment is to create expenses and incomes
 */

class NewMovementFragment: Fragment() {

    private val args: NewMovementFragmentArgs by navArgs()
    private val repository = AccountActivityRepository.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_movement, container, false)

        //Show the dialog fragment to add incomes
        view.addButton.setOnClickListener{
            val fragment = NewMovementDialog()
            fragment.show(fragmentManager, null)
        }

        view.btnSave.setOnClickListener {
            val name = view.movName.text.toString()
            val description = view.movDescription.text.toString()
            var total = 0.0

            for(item in itemList){
                total += item.total
            }

            val movs = Movs(name, description, total, itemList)
            val response = when(args.option){
                "incomes" -> repository.createNewIncome(movs)
                "expenses" -> repository.createNewExpense(movs)
                else -> throw UnselectedMovementTypeException()
            }

        }

        return view
    }

    class UnselectedMovementTypeException:
        Exception("This a serius problem,  you havent select if it is income or expense")

    companion object{
        const val TAG = "NewMovementFragment"
        var itemList = mutableListOf<ItemMov>()
    }
}