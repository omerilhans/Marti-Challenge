package com.omerilhanli.martichallenge.extensive

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("runStatement")
fun View.runStatement(callFunc: () -> Unit) {
    callFunc()
}