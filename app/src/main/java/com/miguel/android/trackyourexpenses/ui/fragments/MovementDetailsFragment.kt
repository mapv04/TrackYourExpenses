package com.miguel.android.trackyourexpenses.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.repository.MovementRepository
import com.miguel.android.trackyourexpenses.databinding.FragmentMovementsDetailsBinding
import com.miguel.android.trackyourexpenses.viewmodel.MovItemViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MovementDetailsFragment: Fragment() {

    private val args: MovementDetailsFragmentArgs by navArgs()
    val repository = MovementRepository()
    private lateinit var model: MovItemViewModel
    private lateinit var binding: FragmentMovementsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movementId = args.movementId


        model = activity?.run {
            ViewModelProviders.of(this).get(MovItemViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movements_details, container,false)

        binding.apply {
            this.lifecycleOwner = this@MovementDetailsFragment
            this.viewmodel = model
        }

        binding.btnOk.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_movementDetailsFragment_to_accountDetailsFragment)
        }



        model.movement.observe(this, Observer {movement ->
            movement.movs?.let{
                for (item in it){
                    val newTextView = TextView(activity)
                    newTextView.apply {
                        @SuppressLint("SetTextI18n")
                        text = "${item.item} - ${item.name} - ${item.total}"
                    }
                    binding.movementsItemContainer.addView(newTextView)
                }
            }
        })

        if(args.movementType == "income"){
            model.getIncomeData()
        }
        else{
            model.getExpenseData()
        }


        return binding.root
    }

    companion object{
        lateinit var movementId: String
    }
}