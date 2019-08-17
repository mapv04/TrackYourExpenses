package com.miguel.android.trackyourexpenses.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.databinding.FragmentNewAccountBinding
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.viewmodel.NewAccountViewModel
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import kotlinx.android.synthetic.main.fragment_new_account.*
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewAccountFragment : Fragment(){

    private lateinit var model: NewAccountViewModel
    private lateinit var  binding: FragmentNewAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var gradientColor = 0

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_account, container, false)
        binding.apply {
            this.lifecycleOwner = this@NewAccountFragment
        }

        binding.save.setOnClickListener {
            binding.title.text.let{
                if (it.isNotBlank()){
                    val newAccount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Accounts("",
                            it.toString(),
                            gradientColor,
                            LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString(),
                            ""
                        )
                    } else {
                        Accounts("",
                            it.toString(),
                            gradientColor,
                            SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time),
                            ""
                        )
                    }
                    model.addNewAccount(newAccount)

                    view?.findNavController()?.navigate(R.id.action_newAccountFragment_to_dashboardFragment)
                }
            }
        }

        binding.colorPicker.setOnClickListener {
            val colorPicker = ColorPicker(activity)
            colorPicker.show()
            colorPicker.enableAutoClose()
            colorPicker.setCallback { color ->
                val alpha = 95 // to make de color more transparent
                val red = Color.red(color)
                val green = Color.green(color)
                val blue = Color.blue(color)
                it.setBackgroundColor(Color.argb(alpha, red, green, blue))
                gradientColor = Color.argb(alpha, red, green, blue)
            }



        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = InjectorUtils.provideNewAccountViewModelFactory(requireContext())
        model = activity?.run{
            ViewModelProviders.of(this, factory).get(NewAccountViewModel::class.java)
        } ?: throw Exception("Invalid activity")

    }



}