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
import com.miguel.android.trackyourexpenses.data.api.request.RequestSignUp
import com.miguel.android.trackyourexpenses.data.database.entity.User
import com.miguel.android.trackyourexpenses.viewmodel.RegisterViewModel
import com.miguel.android.trackyourexpenses.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment: Fragment() {

    private lateinit var model: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.apply {
            this.lifecycleOwner = this@RegisterFragment
            this.viewmodel = model
        }


        model.responseRegister.observe(this, Observer {
            when(it){
                201 -> view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
                409 -> binding.usernameEditText.error = getString(R.string.user_exists)
                else -> Toast.makeText(activity, R.string.check_network_connection, Toast.LENGTH_SHORT).show()
            }
        })

        binding.registerButton.setOnClickListener {
            val user = User("",
                binding.nameEditText.text.toString(),
                binding.lastnameEditText.text.toString(),
                binding.usernameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
                )

            model.register(RequestSignUp(user))
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

}



