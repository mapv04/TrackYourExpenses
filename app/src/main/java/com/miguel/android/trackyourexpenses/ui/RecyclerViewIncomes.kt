package com.miguel.android.trackyourexpenses.ui

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
import com.miguel.android.trackyourexpenses.data.database.entity.Income
import com.miguel.android.trackyourexpenses.ui.viewmodel.IncomesViewModel
import kotlinx.android.synthetic.main.fragment_incomes_list.view.*

class RecyclerViewIncomes(private val accountId: String): Fragment() {

    private lateinit var model: IncomesViewModel
    private lateinit var incomeAdapter: RecyclerViewIncomes.IncomeAdapter
    private var listIncomes = emptyList<Income>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = InjectorUtils.provideIncomesViewModelFactory(accountId)
        model = activity?.run {
            ViewModelProviders.of(this, factory).get(IncomesViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_incomes_list, container, false)

        incomeAdapter = IncomeAdapter(listIncomes)

        view.mRecyclerViewIncome.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = incomeAdapter
        }

        view.fab.setOnClickListener{
            it.findNavController().navigate(R.id.action_accountDetailsFragment_to_newIncomeFragment)
        }

        model.getAllIncomes().observe(this, Observer {
            val incomeList: List<Income> = it.map { income ->
                Income(income.id, income.name!!, income.date!!,
                    0.0F, income.accountId!! )
            }
            incomeAdapter.setNewIncomes(incomeList)
        })


        return view
    }

    private inner class IncomeAdapter(listIncomes: List<Income>): RecyclerView.Adapter<IncomeAdapter.IncomeHolder>(){
        var list : List<Income> = listIncomes

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeHolder {
            val inflater = LayoutInflater.from(parent.context)
            return IncomeHolder(inflater, parent)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: IncomeHolder, position: Int) {
            val income = list[position]
            holder.bind(income)
        }

        fun setNewIncomes(newIncomes: List<Income>){
            list = newIncomes
            notifyDataSetChanged()
        }


        private inner class IncomeHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.movement_item, parent, false)){
            private var mIncomeName: TextView? = null
            private var mIncomeDate: TextView? = null
            private var mIncomeTotal: TextView? = null

            init{
                mIncomeName = itemView.findViewById(R.id.movementName)
                mIncomeDate = itemView.findViewById(R.id.movementDate)
                mIncomeTotal = itemView.findViewById(R.id.movementTotal)
            }

            fun bind(income: Income){
                mIncomeName?.text = income.name
                mIncomeDate?.text = income.date
                mIncomeTotal?.text = income.total.toString()
            }
        }
    }
}