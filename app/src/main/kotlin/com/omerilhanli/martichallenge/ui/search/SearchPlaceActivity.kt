package com.omerilhanli.martichallenge.ui.search

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivitySearchPlaceBinding
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import javax.inject.Inject

class SearchPlaceActivity : BaseActivity<SearchPlaceViewModel>(), SearchPlaceNavigator {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySearchPlaceBinding>(
            this,
            R.layout.activity_search_place
        ).apply {
            lifecycleOwner = this@SearchPlaceActivity
            viewModel = this@SearchPlaceActivity.viewModel
            handler = this@SearchPlaceActivity.viewModel
        }
        viewModel.navigator = this

    }

    override fun getViewModel(): SearchPlaceViewModel {
        viewModel = ViewModelProvider(this, factory).get(SearchPlaceViewModel::class.java)
        return viewModel
    }

}
