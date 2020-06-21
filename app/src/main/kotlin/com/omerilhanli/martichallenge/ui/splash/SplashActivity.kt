package com.omerilhanli.martichallenge.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.omerilhanli.ktx_common.extensive.DURATION_TIME_MILLIS
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivitySplashBinding
import com.omerilhanli.martichallenge.extensive.startThis
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import com.omerilhanli.martichallenge.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashViewModel>(), SplashNavigator {

    private lateinit var binding: ActivitySplashBinding

    private val handler: Handler? = Handler()

    private val runnable = Runnable {
        startThis(MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
            .apply {
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
