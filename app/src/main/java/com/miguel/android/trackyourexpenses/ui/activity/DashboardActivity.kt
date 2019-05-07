package com.miguel.android.trackyourexpenses.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.miguel.android.trackyourexpenses.database.entity.User
import com.miguel.android.trackyourexpenses.ui.DashboardFragment

class DashboardActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = DashboardFragment.newInstance()




}

