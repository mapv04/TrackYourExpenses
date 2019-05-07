package com.miguel.android.trackyourexpenses.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.activity.DashboardActivity
import com.miguel.android.trackyourexpenses.ui.activity.NewAccountActivity
import com.miguel.android.trackyourexpenses.utils.InjectorUtils
import com.miguel.android.trackyourexpenses.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_account_list.view.*

class DashboardFragment : Fragment() {

    private lateinit var model: DashboardViewModel
    private lateinit var accountAdapter: AccountAdapter
    private var userId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account_list, container, false)


        //list test
        /*val list = listOf(
            Accounts(1,"Cuenta de ejemplo",0 ,"sfsdnsdn","02/05/2019",0),
            Accounts(1,"Cuenta de ejemplo2",0 ,"dsafsd00","01/05/2019",0)

            )*/
        accountAdapter = AccountAdapter(emptyList())

        view.mRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = accountAdapter
        }

        view.fab.setOnClickListener {
            val intent = Intent(activity, NewAccountActivity::class.java)
            intent.putExtra(NewAccountFragment.EXTRA_USER_ID, userId)
            startActivity(intent)
        }

        model.getAllAccounts().observe(this, Observer {
            accountAdapter.setNewAccounts(it)
        })

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = activity?.intent?.extras?.getInt(EXTRA_USER_ID)
        Log.i(TAG, "Dashboard for user: $userId")

        val factory = InjectorUtils.provideDashboardViewModelFactory(requireContext(), userId!!)
        model = activity?.run {
            ViewModelProviders.of(this, factory).get(DashboardViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

    private class AccountAdapter(private var list: List<Accounts>) : RecyclerView.Adapter<AccountAdapter.AccountHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
            val inflater = LayoutInflater.from(parent.context)
            return AccountHolder(inflater, parent)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: AccountHolder, position: Int) {
            val account: Accounts = list[position]
            holder.bind(account)
        }

        fun setNewAccounts(newAccounts: List<Accounts>){
            this.list = newAccounts
            notifyDataSetChanged()
        }



        private class AccountHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.account_item, parent, false)){

            private var mTitle: TextView? = null
            private var mLastUpdate: TextView? = null
            private var mCardView: CardView? = null

            init {
                mTitle = itemView.findViewById(R.id.title)
                mLastUpdate = itemView.findViewById(R.id.last_update)
                mCardView = itemView.findViewById(R.id.carview)
            }

            fun bind(account: Accounts){
                mCardView?.setCardBackgroundColor(account.color)
                mTitle?.text = account.name
                mLastUpdate?.text = account.lastUpdate
            }
        }
    }

    companion object{
        private const val TAG="DashboardFragment"
        const val EXTRA_USER_ID = "com.miguel.android.moneymanager.ui.user_id"
        fun newInstance() = DashboardFragment()
    }


}