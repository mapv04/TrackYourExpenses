package com.miguel.android.trackyourexpenses.ui.activity

import com.miguel.android.trackyourexpenses.ui.AccountDetailsFragment

class AccountDetailsActivity: SingleFragmentActivity() {
    override fun createFragment() = AccountDetailsFragment.newInstance()
}