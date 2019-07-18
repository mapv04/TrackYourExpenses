package com.miguel.android.trackyourexpenses.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.data.api.response.ResponseAuth
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesClient
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import com.miguel.android.trackyourexpenses.viewmodel.RegisterViewModel
import com.miguel.android.trackyourexpenses.databinding.FragmentRegisterBinding
import com.miguel.android.trackyourexpenses.common.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RegisterFragment: Fragment() {

    private lateinit var model: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding
    lateinit var expensesService: ExpensesService
    lateinit var expensesClient: ExpensesClient


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.apply {
            this.lifecycleOwner = this@RegisterFragment
            this.viewmodel = model
        }

        // Add the new user to Room
       model.user.observe(this, Observer {
           if(it != null){
               val requestSignUp = RequestSignUp(it)
               val call = expensesService.doSignUp(requestSignUp)

               call.enqueue(object: Callback<ResponseAuth>{

                       override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
                           if(response.isSuccessful){
                               SharedPreferencesManager.setSomeStringValue(PREF_NAME, response.body()?.name.toString() + " " + response.body()?.lastname.toString())
                               SharedPreferencesManager.setSomeStringValue(PREF_USERNAME, response.body()?.username.toString())
                               Toast.makeText(activity, R.string.sign_up_success, Toast.LENGTH_SHORT)
                               model.onUserCreated()
                               view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
                           }
                           else{
                               Toast.makeText(activity, response.body()?.message.toString(), Toast.LENGTH_SHORT).show()
                           }
                       }

                       override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                           if (t is IOException) {
                               Toast.makeText(activity, R.string.check_network_connection, Toast.LENGTH_SHORT).show()
                           }
                           else {
                               Toast.makeText(activity, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show()
                           }
                       }
                   })

                //}

           }

       })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = InjectorUtils.provideRegisterViewModelFactory(requireContext())
        model = activity?.run {
            ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        retrofitInit()

    }

    private fun retrofitInit(){
        expensesClient = ExpensesClient.instance
        expensesService = expensesClient.expensesService
    }

    private fun clearData(){
        binding.emailEditText.text = null
        binding.nameEditText.text = null
        binding.passwordEditText.text = null
        binding.usernameEditText.text = null
    }

    companion object{
        private const val TAG = "RegisterFragment"
        fun newInstance() = RegisterFragment()
    }

}



