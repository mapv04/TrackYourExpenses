package com.miguel.android.trackyourexpenses.ui

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.activity.NewAccountActivity
import com.miguel.android.trackyourexpenses.utils.InjectorUtils
import com.miguel.android.trackyourexpenses.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_account_list.*
import kotlinx.android.synthetic.main.fragment_account_list.view.*

class DashboardFragment : Fragment() {

    private lateinit var model: DashboardViewModel
    private lateinit var accountAdapter: AccountAdapter
    private var userId: Int? = null
    private var mDeleteCallBack: onDeleteAccountListener? = null
    private var mCallBack: Callbacks? = null

    /**
     * Required interface for hosting activities
     */
    interface Callbacks{
        fun onAccountSelected(account: Accounts?)
    }
    interface onDeleteAccountListener{
        fun onAccountIdSelected(account: Accounts)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mCallBack = context as Callbacks?
        mDeleteCallBack = context as onDeleteAccountListener?
    }

    override fun onDetach() {
        super.onDetach()
        mCallBack = null
        mDeleteCallBack = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account_list, container, false)

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

        setAccountRecyclerViewItemTouchListener(view)

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

    private fun setAccountRecyclerViewItemTouchListener(v: View){
        val itemTouch = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val account = accountAdapter.list[position]
                mDeleteCallBack?.onAccountIdSelected(account)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                val itemView = viewHolder.itemView

                val background = ColorDrawable(Color.RED)
                val dx = dX.toInt()
                background.setBounds(0, itemView.top, itemView.left + dx, itemView.bottom)
                background.draw(c)
                val icon = ContextCompat.getDrawable(context!!,R.drawable.ic_delete_black_24dp)
                icon?.setBounds(viewHolder.itemView.left - 50, viewHolder.itemView.top, viewHolder.itemView.right - 800, viewHolder.itemView.bottom)
                icon?.draw(c)
            }

        }

        val itemTouchHelper  = ItemTouchHelper(itemTouch)
        itemTouchHelper.attachToRecyclerView(v.mRecyclerView)
    }

    fun deleteAccount(account: Accounts){
        model.deleteAccount(account)
    }

    private inner class AccountAdapter(listAccounts: List<Accounts>) : RecyclerView.Adapter<AccountAdapter.AccountHolder>() {
        var list: List<Accounts> = listAccounts

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
            list = newAccounts
            notifyDataSetChanged()
        }

        private inner class AccountHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.account_item, parent, false)), View.OnClickListener{

            private var mTitle: TextView? = null
            private var mLastUpdate: TextView? = null
            private var mCardView: CardView? = null
            private var mAccount: Accounts? = null

            init {
                itemView.setOnClickListener(this)
                mTitle = itemView.findViewById(R.id.title)
                mLastUpdate = itemView.findViewById(R.id.last_update)
                mCardView = itemView.findViewById(R.id.carview)
            }

            fun bind(account: Accounts){
                mAccount = account
                mCardView?.setCardBackgroundColor(mAccount!!.color)
                mTitle?.text = mAccount?.name
                mLastUpdate?.text = mAccount?.lastUpdate
            }

            override fun onClick(v: View?) {
                this@DashboardFragment.mCallBack?.onAccountSelected(mAccount)
            }
        }
    }


    companion object{
        private const val TAG="DashboardFragment"
        const val EXTRA_USER_ID = "com.miguel.android.moneymanager.ui.user_id"
        fun newInstance() = DashboardFragment()
    }


}