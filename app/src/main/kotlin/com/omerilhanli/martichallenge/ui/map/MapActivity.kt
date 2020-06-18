package com.omerilhanli.martichallenge.ui.map

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivityMapBinding
import com.omerilhanli.martichallenge.databinding.ActivitySearchPlaceBinding
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import javax.inject.Inject

class MapActivity : BaseActivity<MapViewModel>(), MapNavigator  {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMapBinding>(this, R.layout.activity_map).apply {
            lifecycleOwner = this@MapActivity
            viewModel = this@MapActivity.viewModel
            handler = this@MapActivity.viewModel
        }
        viewModel.navigator = this
    }


    override fun getViewModel(): MapViewModel {
        viewModel = ViewModelProvider(this, factory).get(MapViewModel::class.java)
        return viewModel
    }
}
