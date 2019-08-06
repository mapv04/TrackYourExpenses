package com.miguel.android.trackyourexpenses.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.Movs
import kotlinx.android.synthetic.main.fragment_new_item.*
import kotlinx.android.synthetic.main.fragment_new_item.view.*

class NewMovementDialog(private val newMovementFragment: NewMovementFragment): DialogFragment() {
    private var itemListCallback: OnItemListListener? = null

    interface OnItemListListener{
        fun onItemListCreated(item: ItemMov, movementFragment: NewMovementFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemListCallback = context as OnItemListListener
    }

    override fun onDetach() {
        super.onDetach()
        itemListCallback = null
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var builder: AlertDialog.Builder
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_new_item, null)

        activity?.let {
            builder = AlertDialog.Builder(it)
            builder.setView(view)

            view?.btnCancel?.setOnClickListener {
                dismiss()
            }

            view?.btnAdd?.setOnClickListener {
                val item = ItemMov(
                    view.infoEditText?.text.toString(),
                    view.placeEditText?.text.toString(),
                    view.totalEditText?.text.toString().toDouble()
                )
                itemListCallback?.onItemListCreated(item, newMovementFragment)

                dismiss()
            }

        }

        return builder.create()
    }
}