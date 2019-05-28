package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.R

abstract class SingleFragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment

    @LayoutRes
    protected fun getLayoutResId() = R.layout.activity_fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        val fragmentManager = supportFragmentManager

        /*var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null){
            fragment = createFragment()
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }*/

    }
}