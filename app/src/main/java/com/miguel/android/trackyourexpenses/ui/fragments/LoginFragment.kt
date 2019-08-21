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
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : Fragment() {

    private lateinit var model: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var repository: AuthRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            this.lifecycleOwner = this@LoginFragment
            this.viewmodel = model
        }

        binding.mLogin.setOnClickListener {
            val username = mUsername.text.toString()
            val password = mPassword.text.toString()

            if(!username.isNullOrEmpty() && !password.isNullOrEmpty()){
                val requestLogin = RequestLogin(username, password)
                it.isEnabled = false
                loading.visibility = View.VISIBLE
                model.doLogin(requestLogin)
            }
            else{
                Toast.makeText(activity, R.string.enter_all_values, Toast.LENGTH_SHORT).show()

            }
        }


        model.responseLogin.observe(this, Observer{
            binding.mLogin.isEnabled = true
            loading.visibility = View.GONE
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

        (activity?.application as MyApp).getComponent().getAuthRepository(this)

        if(SharedPreferencesManager.getSomeBoolValue(PREF_KEEP_LOGIN)){
            //Keep Login
            findNavController().navigate(R.id.action_loginFragment_to_accountActivity)
        }


        val factory = InjectorUtils.provideLoginViewModelFactory(repository)
        model = activity?.run{
            ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }


}
