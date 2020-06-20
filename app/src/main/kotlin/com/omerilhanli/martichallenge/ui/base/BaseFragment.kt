package com.omerilhanli.martichallenge.ui.base

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T : ViewModel> : DaggerFragment() {

    private var viewModel: T? = null

    abstract fun getViewModel(): T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = getViewModel()
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

}