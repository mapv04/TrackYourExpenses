package com.miguel.android.trackyourexpenses.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.viewmodel.RegisterViewModel
import com.miguel.android.trackyourexpenses.databinding.FragmentRegisterBinding
import com.miguel.android.trackyourexpenses.ui.activity.LoginActivity
import com.miguel.android.trackyourexpenses.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment: Fragment() {

    private lateinit var model: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Add the new user to Room
       model.user.observe(this, Observer {
           if(model.userExists(it.username) > 0){
               // The user already exists
               Toast.makeText(activity, R.string.user_exists, Toast.LENGTH_LONG).show()
           } else{
               //ADD THE NEW USER
               model.addNewUser(it)
               Toast.makeText(activity, R.string.sign_up_success, Toast.LENGTH_SHORT).show()
               val intent = Intent(activity, LoginActivity::class.java)
               startActivity(intent)
               activity?.finish()
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

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        binding.apply {
            this.lifecycleOwner = this@RegisterFragment
            this.viewmodel = model
        }

    }



    companion object{
        private const val TAG = "RegisterFragment"
        fun newInstance() = RegisterFragment()
    }

}


