package com.iml.myapplication

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object AppBinding {

    @JvmStatic
    @BindingAdapter("showErrorMsg")
    fun setErrorMessage(textInputLayout: TextInputLayout, message: String?) {
        if (!message.isNullOrEmpty() && textInputLayout.isErrorEnabled) {
            textInputLayout.error = message
        }
    }
}