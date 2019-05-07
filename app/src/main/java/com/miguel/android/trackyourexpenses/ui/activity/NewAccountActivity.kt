package com.miguel.android.trackyourexpenses.ui.activity

import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.ui.NewAccountFragment

class NewAccountActivity: SingleFragmentActivity() {
    override fun createFragment(): Fragment = NewAccountFragment.newInstance()
}