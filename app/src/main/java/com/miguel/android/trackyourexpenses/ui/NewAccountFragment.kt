package com.miguel.android.trackyourexpenses.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miguel.android.trackyourexpenses.R
import com.miguel.android.trackyourexpenses.data.database.entity.Accounts
import com.miguel.android.trackyourexpenses.databinding.FragmentNewAccountBinding
import com.miguel.android.trackyourexpenses.ui.activity.DashboardActivity
import com.miguel.android.trackyourexpenses.common.InjectorUtils
import com.miguel.android.trackyourexpenses.viewmodel.NewAccountViewModel
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import kotlinx.android.synthetic.main.fragment_new_account.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewAccountFragment : Fragment(){

    private lateinit var model: NewAccountViewModel
    private lateinit var  binding: FragmentNewAccountBinding
    private var imageEncoded = ""
    private var userId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var gradientColor = 0

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_account, container, false)
        binding.apply {
            this.lifecycleOwner = this@NewAccountFragment
            this.viewmodel = model
        }

        binding.addImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
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
                val gradient: GradientDrawable = it.background.mutate() as GradientDrawable
                gradient.setColor(Color.argb(alpha, red, green, blue))
                gradientColor = Color.argb(alpha, red, green, blue)
            }

            model.accountName.observe(this, Observer {
                val newAccount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Accounts(null,
                        it,
                        gradientColor,
                        imageEncoded, LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString(),
                        userId!!
                    )
                } else {
                    Accounts(null,
                        it,
                        gradientColor,
                        imageEncoded,
                        SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time),
                        userId!!
                    )
                }
                model.addNewAccount(newAccount)

                Log.i(TAG, "New account created")

                // Return to Dashboard
                val intent = Intent(activity, DashboardActivity::class.java)
                intent.putExtra(DashboardFragment.EXTRA_USER_ID, userId!!)
                startActivity(intent)
                activity?.finish()
            })
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = activity?.intent?.extras?.getInt(EXTRA_USER_ID)
        val factory = InjectorUtils.provideNewAccountViewModelFactory(requireContext())
        model = activity?.run{
            ViewModelProviders.of(this, factory).get(NewAccountViewModel::class.java)
        } ?: throw Exception("Invalid activity")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null){
            val uri = data.data
            Log.d(TAG, "URI DATA: $uri")
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
                addImage.setImageBitmap(bitmap)
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val byte = baos.toByteArray()
                imageEncoded = Base64.encodeToString(byte, Base64.DEFAULT)
            }catch(e: IOException){
                Log.e(TAG, "Error onActivityResult: ",e)
            }
        }
    }

    companion object{
        const val EXTRA_USER_ID = "com.miguel.android.moneymanager.ui.user_id"
        private const val PICK_IMAGE_REQUEST = 1
        private const val TAG = "NewAccountFragment"
        fun newInstance() = NewAccountFragment()
    }

}