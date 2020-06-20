package com.omerilhanli.martichallenge.ui.base

import android.app.AlertDialog
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.omerilhanli.ktx_common.AppProgressBar
import com.omerilhanli.martichallenge.R
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.HttpException

abstract class BaseActivity<T : BaseViewModel<*>> : DaggerAppCompatActivity(), BaseNavigator {

    abstract fun getViewModel(): T

    private var viewModel: T? = null
    private val appProgressBar = AppProgressBar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = if (viewModel == null) getViewModel() else viewModel

        this.viewModel?.isLoading?.observe(this, Observer { value ->
            if (value)
                showPd()
            else
                dismissPd()
        })
    }

    fun showPd() {
        try {
            if (!isFinishing && !isDestroyed) {
                if (appProgressBar.dialog == null || !appProgressBar.dialog!!.isShowing)
                    appProgressBar.show(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissPd() {
        try {
            if (!isFinishing && !isDestroyed)
                appProgressBar.dialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            fragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .remove(fragment)
                .commitNow()
        }
        //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
    }

    fun replaceFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment, @Nullable tag: String) {
        supportFragmentManager
            .beginTransaction()
            .disallowAddToBackStack()
            .replace(containerViewId, fragment, tag)
            .commitAllowingStateLoss()
        //.setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
    }

    override fun onFragmentDetached(tag: String) {
        removeFragment(tag)
    }

    override fun handleError(error: Throwable) {
        showErrorMessage(getMessageFromError(error))
    }

    fun showErrorMessage(message: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.Generic_err))
            .setMessage(message)
            .setPositiveButton(getString(R.string.Generic_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    // TODO -> error mesajlar düzeltilecek !
    fun getMessageFromError(error: Throwable): String {

        var message: String? = null

        when (error) {
            is HttpException -> {
                if (error.response()?.code() == 400) {
                    message = resources.getString(R.string.Generic_Error)
                } else {
                    message = resources.getString(R.string.Generic_Error_2)
                }
            }
            else -> {
                message = resources.getString(R.string.Generic_Error_3)
            }
        }

        return message
    }
}