package com.miguel.android.trackyourexpenses

import android.widget.Button
import android.widget.EditText
import androidx.databinding.BindingAdapter

 @BindingAdapter("emptyField", "error")
    fun checkEmptyField(editText: EditText, text: String?, errorMessage: String){
     if (text != null && text.isEmpty()){
         editText.error = errorMessage
     }
    }

