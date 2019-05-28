package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.databinding.ActivityFragmentBinding
import com.miguel.android.trackyourexpenses.ui.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityFragmentBinding>(this, R.layout.activity_fragment)

    }

    //override fun createFragment(): Fragment = LoginFragment.newInstance()

}
