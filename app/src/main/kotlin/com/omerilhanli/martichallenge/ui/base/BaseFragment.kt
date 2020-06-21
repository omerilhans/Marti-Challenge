package com.omerilhanli.martichallenge.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.omerilhanli.ktx_common.dialog.AppProgressBar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel<*>> : DaggerFragment() {

    @Inject
    lateinit var viewModel: T

    private val appProgressBar = AppProgressBar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isLoading.observe(this, Observer { value ->
            if (value)
                showPd()
            else
                dismissPd()
        })
    }

    private fun showPd() {
        try {
            if (activity?.isFinishing == false && activity?.isDestroyed == false) {
                if (appProgressBar.dialog == null || !appProgressBar.dialog!!.isShowing)
                    appProgressBar.show(requireContext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun dismissPd() {
        try {
            if (activity?.isFinishing == false && activity?.isDestroyed == false)
                appProgressBar.dialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

}