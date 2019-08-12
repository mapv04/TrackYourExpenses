package com.miguel.android.trackyourexpenses.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.miguel.android.trackyourexpenses.viewmodel.LoginViewModel
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.databinding.FragmentLoginBinding
import com.miguel.android.trackyourexpenses.common.*
import com.miguel.android.trackyourexpenses.data.repository.AuthRepository


class LoginFragment : Fragment() {

    private lateinit var model: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private val repository = AuthRepository()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            this.lifecycleOwner = this@LoginFragment
            this.viewmodel = model
        }

        model.user.observe(this, Observer {
            val requestLogin = RequestLogin(it.username, it.password)

            model.doLogin(requestLogin)

        })

        model.responseLogin.observe(this, Observer{
            when (it) {
                200 -> {
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_accountActivity)
                    activity?.finish()
                }
                401, 404 -> {
                    binding.mUsername.error = getText(R.string.authentication_fail)
                }
                else -> Toast.makeText(activity, R.string.check_network_connection, Toast.LENGTH_SHORT).show()
            }
        })

        binding.mSignUp.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
        )

        //Switch Login
        binding.switchLogin.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked){
                SharedPreferencesManager.setSomeBoolValue(PREF_KEEP_LOGIN, true)
            }
            else{
                SharedPreferencesManager.setSomeBoolValue(PREF_KEEP_LOGIN, false)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if(SharedPreferencesManager.getSomeBoolValue(PREF_KEEP_LOGIN)){
            //Keep Login
            findNavController().navigate(R.id.action_loginFragment_to_accountActivity)
        }


        val factory = InjectorUtils.provideLoginViewModelFactory()
        model = activity?.run{
            ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }




    companion object{
        private const val TAG = "LoginFragment"
        const val EXTRA_USER_ID = "com.miguel.android.moneymanager.ui.activity.user_id"
    }
}
