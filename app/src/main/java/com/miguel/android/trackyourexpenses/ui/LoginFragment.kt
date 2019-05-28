package com.miguel.android.trackyourexpenses.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.miguel.android.trackyourexpenses.viewmodel.LoginViewModel
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.databinding.FragmentLoginBinding
import com.miguel.android.trackyourexpenses.ui.activity.DashboardActivity
import kotlinx.android.synthetic.main.fragment_login.*
import com.miguel.android.trackyourexpenses.utils.InjectorUtils

class LoginFragment : Fragment() {

    private lateinit var model: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            this.lifecycleOwner = this@LoginFragment
            this.viewmodel = model
        }

        model.user.observe(this, Observer {
            Log.i(TAG, "observer...")
            if(!it.username.isNullOrEmpty() && !it.password.isNullOrEmpty()){
                val userFound = model.authentication(it.username, it.password)
                if (userFound == null) {
                    mUsername.error = getText(R.string.authentication_fail)
                    model.editTextPassword.value = ""
                    Toast.makeText(activity, getText(R.string.authentication_fail), Toast.LENGTH_SHORT).show()
                } else {
                    Log.i(TAG, "Iniciando Dashboard")
                    val intent = Intent(activity, DashboardActivity::class.java)
                    intent.putExtra(DashboardFragment.EXTRA_USER_ID, userFound.id)
                    startActivity(intent)
                    activity?.finish()
                }
            }
        })

        binding.mSignUp.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
        )

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = InjectorUtils.provideLoginViewModelFactory(requireContext())
        model = activity?.run{
            ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }



    companion object{
        private const val TAG = "LoginFragment"
        const val EXTRA_USER_ID = "com.miguel.android.moneymanager.ui.activity.user_id"
        fun newInstance() = LoginFragment()
    }
}
