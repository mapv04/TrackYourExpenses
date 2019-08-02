package com.miguel.android.trackyourexpenses.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.data.database.entity.Expense
import com.miguel.android.trackyourexpenses.ui.viewmodel.ExpensesViewModel
import kotlinx.android.synthetic.main.fragment_expenses_list.view.*

class RecyclerViewExpenses: Fragment() {

    private lateinit var model: ExpensesViewModel
    private lateinit var expenseAdapter: ExpenseAdapter
    private var listExpenses = emptyList<Expense>()
    private var movementListener: OnMovementSelected? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        movementListener = context as OnMovementSelected
    }

    override fun onDetach() {
        super.onDetach()
        movementListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(ExpensesViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_expenses_list, container, false)

        expenseAdapter = ExpenseAdapter(listExpenses)

        view.mRecyclerViewExpense.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = expenseAdapter
        }

        /**
         * Add new expense
         */
        view.fab.setOnClickListener{
            val action = AccountDetailsFragmentDirections.actionAccountDetailsFragmentToNewMovementFragment("expense", AccountDetailsFragment.account)
            it.findNavController().navigate(action)
        }

        model.getAllExpenses().observe(this, Observer {
            val expenseList: List<Expense> = it.map { expense ->
                Expense(expense.id, expense.name!!, expense.description!!, expense.date!!,
                    expense.total!!.toFloat(), expense.accountId!! )
            }
            expenseAdapter.setNewExpenses(expenseList)
        })

        return view
    }

    private inner class ExpenseAdapter(listExpenses: List<Expense>): RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder>(){
        var list : List<Expense> = listExpenses

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ExpenseHolder(inflater, parent)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ExpenseHolder, position: Int) {
            val expense = list[position]
            holder.bind(expense)
        }

        fun setNewExpenses(newExpense: List<Expense>){
            list = newExpense
            notifyDataSetChanged()
        }


        private inner class ExpenseHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.movement_item, parent, false)), View.OnClickListener{
            override fun onClick(v: View?) {
                this@RecyclerViewExpenses.movementListener?.onExpenseIdSelected(mExpense.id, v)
            }

            private var mExpenseName: TextView? = null
            private var mExpenseDate: TextView? = null
            private var mExpenseTotal: TextView? = null
            private lateinit var mExpense: Expense

            init{
                mExpenseName = itemView.findViewById(R.id.movementName)
                mExpenseDate = itemView.findViewById(R.id.movementDate)
                mExpenseTotal = itemView.findViewById(R.id.movementTotal)
            }

            fun bind(expense: Expense){
                mExpense = expense
                mExpenseName?.text = expense.name
                mExpenseDate?.text = expense.date
                mExpenseTotal?.text = expense.total.toString()
            }
        }
    }
}