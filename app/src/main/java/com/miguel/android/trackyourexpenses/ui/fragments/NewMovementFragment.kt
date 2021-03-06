package com.miguel.android.trackyourexpenses.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.common.MyApp
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.Movs
import com.miguel.android.trackyourexpenses.repository.AccountActivityRepository
import com.miguel.android.trackyourexpenses.viewmodel.MovementsViewModel
import kotlinx.android.synthetic.main.fragment_new_movement.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

/**
 * This fragment is to create expenses and incomes
 */

class NewMovementFragment: Fragment() {

    @Inject
    lateinit var repository: AccountActivityRepository

    private lateinit var model: MovementsViewModel
    private val args: NewMovementFragmentArgs by navArgs()
    private val previousArgs: AccountDetailsFragmentArgs by navArgs()

    var itemList = mutableListOf<ItemMov>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MyApp).getComponent().getAccountActivityRepository(this)

        val factory = InjectorUtils.provideMovementsViewModelFactory(repository)

        model = activity?.run {
            ViewModelProviders.of(this, factory).get(MovementsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_movement, container, false)

        //Show the dialog fragment to add incomes
        view.addButton.setOnClickListener{
            val fragment = NewMovementDialog(this@NewMovementFragment)
            fragmentManager?.let{
                fragment.show(it, null)
            }

        }

        view.btnSave.setOnClickListener {
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
                "income" -> model.createNewIncome(movs)
                "expense" -> model.createNewExpense(movs)
                else -> throw UnselectedMovementTypeException()
            }

           val action =
               NewMovementFragmentDirections.actionNewMovementFragmentToAccountDetailsFragment(
                   //previousArgs.accountId
               )
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