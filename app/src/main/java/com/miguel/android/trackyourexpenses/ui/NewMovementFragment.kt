package com.miguel.android.trackyourexpenses.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.navigateUp
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.repository.AccountActivityRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
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
    var itemList = mutableListOf<ItemMov>()

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_movement, container, false)

        //Show the dialog fragment to add incomes
        view.addButton.setOnClickListener{
            val fragment = NewMovementDialog(this@NewMovementFragment)
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



    fun newItem(){
        val linearLayout = view?.movementsContainer
        val newTextView = TextView(activity)
        newTextView.apply {
            text = "${itemList.last().name} - ${itemList.last().item} - ${itemList.last().total}"
        }

        linearLayout?.addView(newTextView)

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


}