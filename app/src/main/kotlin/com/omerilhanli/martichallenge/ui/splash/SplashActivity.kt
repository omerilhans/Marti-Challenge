package com.omerilhanli.martichallenge.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.ktx_common.DURATION_TIME_MILLIS
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivitySplashBinding
import com.omerilhanli.martichallenge.extensive.startThis
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import com.omerilhanli.martichallenge.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>(), SplashNavigator {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel
    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)
        return viewModel
    }

    private lateinit var binding: ActivitySplashBinding

    private val handler: Handler? = Handler()

    private val runnable = Runnable {
        startThis(MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySplashBinding>(
            this,
            R.layout.activity_splash
        ).apply {
            lifecycleOwner = this@SplashActivity
            viewModel = this@SplashActivity.viewModel
            handler = this@SplashActivity.viewModel
        }

        viewModel.navigator = this
    }

    override fun navigateMainActivity() {
        handler?.postDelayed(runnable, DURATION_TIME_MILLIS)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.let { handler ->
            runnable.let {
                handler.removeCallbacks(it)
            }
        }
    }
}
