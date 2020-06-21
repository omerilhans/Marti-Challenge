package com.omerilhanli.martichallenge.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.ktx_common.extensive.KEY_INTENT_PLACE_DETAIL
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.FragmentDetailBinding
import com.omerilhanli.martichallenge.ui.base.BaseFragment
import com.omerilhanli.martichallenge.ui.main.MainActivity
import com.omerilhanli.martichallenge.ui.main.MainViewModel

class DetailFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false)
            .apply {
                lifecycleOwner = this@DetailFragment
                viewModel = this@DetailFragment.viewModel
                handler = this@DetailFragment.viewModel
            }

        viewModel.navigator = activity as MainActivity

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        extractDataFromIntent()
    }

    private fun extractDataFromIntent() {
        viewModel.placeDetailResponse = arguments?.getSerializable(KEY_INTENT_PLACE_DETAIL) as PlaceDetailResponse
    }

    companion object {

        fun newInstance(placeDetail: PlaceDetailResponse): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_INTENT_PLACE_DETAIL, placeDetail)
            fragment.arguments = bundle
            return fragment
        }
    }

}