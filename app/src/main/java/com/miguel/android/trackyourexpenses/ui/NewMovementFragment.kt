package com.miguel.android.trackyourexpenses.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.navigateUp
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository
import kotlinx.android.synthetic.main.fragment_new_movement.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * This fragment is to create expenses and incomes
 */

class NewMovementFragment: Fragment() {

    private val args: NewMovementFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_movement, container, false)

        //Show the dialog fragment to add incomes
        view.addButton.setOnClickListener{
            val fragment = NewMovementDialog()
            fragment.show(fragmentManager, null)
        }

        view.btnSave.setOnClickListener {
            val repository = AccountActivityRepository()
            val name = view.movName.text.toString()
            val description = view.movDescription.text.toString()

            val date = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
            }
            else{
                SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)
            }

            val total = getTotal()

            val movs = Movs(name, description, date, total, itemList)

            when(args.option){
                "income" -> repository.createNewIncome(movs)
                "expense" -> repository.createNewExpense(movs)
                else -> throw UnselectedMovementTypeException()
            }

           val action = NewMovementFragmentDirections.actionNewMovementFragmentToAccountDetailsFragment(args.accountId)
            view.findNavController().navigate(action)

        }

        return view
    }

    private fun getTotal(): Double{
        var total = 0.0
        for(item in itemList){
            total += item.total
        }
        return total
    }

    class UnselectedMovementTypeException:
        Exception("This a serius problem,  you havent select if it is income or expense")

    companion object{
        const val TAG = "NewMovementFragment"
        var itemList = mutableListOf<ItemMov>()
    }
}