package com.miguel.android.trackyourexpenses.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.MyApp
import com.miguel.android.trackyourexpenses.data.ItemMov
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.ui.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.navhost)

        findNavController(R.id.myNavHostFragment).setGraph(R.navigation.navigation_auth)
    }



}
