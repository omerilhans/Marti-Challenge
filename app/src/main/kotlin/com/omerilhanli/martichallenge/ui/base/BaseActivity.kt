package com.omerilhanli.martichallenge.ui.base

import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.omerilhanli.ktx_common.dialog.AppAlert
import com.omerilhanli.martichallenge.R
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel<*>> : DaggerAppCompatActivity(), BaseNavigator {

    @Inject
    lateinit var viewModel: T

    fun replaceFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment, @Nullable tag: String) {
        supportFragmentManager
            .beginTransaction()
            .disallowAddToBackStack()
            .replace(containerViewId, fragment, tag)
            .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
            .commitAllowingStateLoss()
    }

    private fun removeFragment(tag: String) {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            fragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .remove(fragment)
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .commitNow()
        }
    }

    override fun onFragmentDetached(tag: String) {
        removeFragment(tag)
    }

    override fun handleError(error: Throwable) {
        showErrorMessage(getMessageFromError(error))
    }

    private fun showErrorMessage(message: String) {
        AppAlert.showAlert(this, getString(R.string.Generic_err), message, getString(R.string.Generic_ok))
    }

    private fun getMessageFromError(error: Throwable): String {
        var message = ""
        when (error) {
            is HttpException -> {
                if (error.response()?.code() == 400) {
                    message = resources.getString(R.string.Generic_Error_400)
                }
                if (error.response()?.code() == 403) {
                    message = resources.getString(R.string.Generic_Error_403)
                }
                if (error.response()?.code() == 404) {
                    message = resources.getString(R.string.Generic_Error_404)
                }
                if (error.response()?.code() == 503) {
                    message = resources.getString(R.string.Generic_Error_503)
                }
            }
            is UnknownHostException -> {
                message = resources.getString(R.string.Generic_Network_Error)
            }
            else -> {
                message = resources.getString(R.string.Generic_Error)
            }
        }

        return message
    }
}