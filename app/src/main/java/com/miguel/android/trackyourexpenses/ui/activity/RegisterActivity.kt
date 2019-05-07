package com.miguel.android.trackyourexpenses.ui.activity

import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.ui.RegisterFragment

class RegisterActivity: SingleFragmentActivity() {
    override fun createFragment(): Fragment = RegisterFragment.newInstance()


}