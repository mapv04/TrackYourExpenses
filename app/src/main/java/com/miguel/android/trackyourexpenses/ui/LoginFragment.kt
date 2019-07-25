package com.miguel.android.trackyourexpenses.ui

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
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.ui.viewmodel.LoginViewModel
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.api.request.RequestLogin
import com.miguel.android.trackyourexpenses.data.api.response.ResponseAuth
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import com.miguel.android.trackyourexpenses.databinding.FragmentLoginBinding
import com.miguel.android.trackyourexpenses.common.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class LoginFragment : Fragment() {

    private lateinit var model: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    lateinit var expensesService: ExpensesService
    lateinit var expensesClient: ExpensesClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            this.lifecycleOwner = this@LoginFragment
            this.viewmodel = model
        }

        model.user.observe(this, Observer {
            val requestLogin = RequestLogin(it.username, it.password)
            val call = expensesService.doLogin(requestLogin)

            call.enqueue(object: Callback<ResponseAuth> {
                override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
                    if(response.isSuccessful){
                        SharedPreferencesManager.setSomeStringValue(PREF_TOKEN, response.body()?.token.toString())
                        SharedPreferencesManager.setSomeStringValue(PREF_NAME, response.body()?.name.toString() + " " + response.body()?.lastname.toString())
                        SharedPreferencesManager.setSomeStringValue(PREF_USERNAME, response.body()?.username.toString())
                        view?.findNavController()?.navigate(R.id.action_loginFragment_to_dashboardFragment)
                    }
                    else{
                        Toast.makeText(activity, response.body()?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                    if (t is IOException) {
                        Toast.makeText(activity, R.string.check_network_connection, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Exception: ${t}")
                    }
                    else {
                        Toast.makeText(activity, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show()
                    }
                }

            })

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

        retrofitInit()
    }

    private fun retrofitInit(){
        expensesClient = ExpensesClient.instance
        expensesService = expensesClient.expensesService
    }



    companion object{
        private const val TAG = "LoginFragment"
        const val EXTRA_USER_ID = "com.miguel.android.moneymanager.ui.activity.user_id"
    }
}
