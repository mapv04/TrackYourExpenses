package com.miguel.android.trackyourexpenses.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.databinding.FragmentAccountDetailsBinding

class AccountDetailsFragment: Fragment() {

    private val args: AccountDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentAccountDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_details, container, false)

        binding.apply {
            this.lifecycleOwner = this@AccountDetailsFragment
            //this.viewmodel = model
        }

        val account = args.account
        binding.accountName.text = account.name

        return binding.root
    }


}