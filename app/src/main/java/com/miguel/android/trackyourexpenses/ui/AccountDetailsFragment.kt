package com.miguel.android.trackyourexpenses.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.data.database.entity.Income
import com.miguel.android.trackyourexpenses.databinding.FragmentAccountDetailsBinding
import com.miguel.android.trackyourexpenses.ui.viewmodel.IncomesViewModel
import kotlinx.android.synthetic.main.fragment_account_details.view.*
import kotlinx.android.synthetic.main.income_item.*

class AccountDetailsFragment: Fragment() {

    private lateinit var model: IncomesViewModel
    private val args: AccountDetailsFragmentArgs by navArgs()
    private lateinit var incomeAdapter: IncomeAdapter
    private var listIncomes = emptyList<Income>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = args.account
        val factory = InjectorUtils.provideIncomesViewModelFactory(account.id)
        model = activity?.run {
            ViewModelProviders.of(this, factory).get(IncomesViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account_details, container, false)

        incomeAdapter = IncomeAdapter(listIncomes)

        view.mRecyclerViewIncome.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = incomeAdapter
        }

        view.fab.setOnClickListener{

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


        private inner class IncomeHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.income_item, parent, false)){
            private var mIncomeName: TextView? = null
            private var mIncomeDate: TextView? = null
            private var mIncomeTotal: TextView? = null

            init{
                mIncomeName = itemView.findViewById(R.id.incomeName)
                mIncomeDate = itemView.findViewById(R.id.incomeDate)
                mIncomeTotal = itemView.findViewById(R.id.incomeTotal)
            }

            fun bind(income: Income){
                mIncomeName?.text = income.name
                mIncomeDate?.text = income.date
                mIncomeTotal?.text = income.total.toString()
            }
        }
    }


}