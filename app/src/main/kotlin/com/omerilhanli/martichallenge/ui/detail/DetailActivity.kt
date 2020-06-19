package com.omerilhanli.martichallenge.ui.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivityDetailBinding
import com.omerilhanli.martichallenge.extensive.KEY_INTENT_PLACE_DETAIL
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailViewModel>(), DetailNavigator {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        extractDataFromIntent()
        //
        configBinding()
        //
        bindData()
    }

    private fun configBinding() {
        binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
                .apply {
                    lifecycleOwner = this@DetailActivity
                    viewModel = this@DetailActivity.viewModel
                    handler = this@DetailActivity.viewModel
                }

        viewModel.navigator = this
    }

    private fun extractDataFromIntent() {
        viewModel.placeDetailResponse = intent?.extras?.getSerializable(KEY_INTENT_PLACE_DETAIL) as PlaceDetailResponse
    }

    private fun bindData(){

    }

    override fun getViewModel(): DetailViewModel {
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        return viewModel
    }

}
