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
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.common.MyApp
import com.miguel.android.trackyourexpenses.data.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.data.database.entity.User
import com.miguel.android.trackyourexpenses.viewmodel.RegisterViewModel
import com.miguel.android.trackyourexpenses.databinding.FragmentRegisterBinding
import com.miguel.android.trackyourexpenses.repository.AuthRepository
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject


class RegisterFragment: Fragment() {

    private lateinit var model: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var repository: AuthRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.apply {
            this.lifecycleOwner = this@RegisterFragment
            this.viewmodel = model
        }


        model.responseRegister.observe(this, Observer {
            binding.registerButton.isEnabled = true
            loading.visibility = View.GONE
            when(it){
                201 -> view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
                409 -> binding.usernameEditText.error = getString(R.string.user_exists)
                else -> Toast.makeText(activity, R.string.check_network_connection, Toast.LENGTH_SHORT).show()
            }
        })

        binding.registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val lName = lastnameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if(!name.isNullOrEmpty() &&
                !lName.isNullOrEmpty() &&
                !username.isNullOrEmpty() &&
                !email.isNullOrEmpty() &&
                !password.isNullOrEmpty()){
                it.isEnabled = false
                loading.visibility = View.VISIBLE
                val user = User("",
                    name,
                    lName,
                    username,
                    email,
                    password
                )

                model.register(RequestSignUp(user))
            } else {
                Toast.makeText(activity, R.string.enter_all_values, Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MyApp).getComponent().getAuthRepository(this)

        val factory = InjectorUtils.provideRegisterViewModelFactory(repository)

        model = activity?.run {
            ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

}



