package com.miguel.android.trackyourexpenses.ui.activity

import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.ui.LoginFragment

class LoginActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = LoginFragment.newInstance()

}
