package com.omerilhanli.martichallenge.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.ktx_common.extensive.KEY_INTENT_PLACE_DETAIL
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.FragmentDetailBinding
import com.omerilhanli.martichallenge.ui.base.BaseFragment
import com.omerilhanli.martichallenge.ui.main.MainViewModel
import javax.inject.Inject

class DetailFragment : BaseFragment<MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun getViewModel(): MainViewModel {
        viewModel = activity?.run {
            ViewModelProvider(activity!!, factory).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        ).apply {
            lifecycleOwner = this@DetailFragment
            viewModel = this@DetailFragment.viewModel
            handler = this@DetailFragment.viewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        extractDataFromIntent()
    }

    private fun extractDataFromIntent() {
        viewModel.placeDetailResponse =
            arguments?.getSerializable(KEY_INTENT_PLACE_DETAIL) as PlaceDetailResponse
    }

    companion object {
        const val TAG: String = "DetailFragment"

        fun newInstance(placeDetail: PlaceDetailResponse): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_INTENT_PLACE_DETAIL, placeDetail)
            fragment.arguments = bundle
            return fragment
        }
    }

}