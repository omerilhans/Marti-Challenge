package com.omerilhanli.martichallenge.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivitySearchPlaceBinding
import com.omerilhanli.martichallenge.extensive.KEY_INTENT_PLACES
import com.omerilhanli.martichallenge.extensive.bindAdapter
import com.omerilhanli.martichallenge.extensive.startThis
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import com.omerilhanli.martichallenge.ui.map.MapActivity
import javax.inject.Inject

class SearchPlacesActivity : BaseActivity<SearchPlacesViewModel>(), SearchPlacesNavigator {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchPlacesViewModel

    private lateinit var adapter: RecyclerPlacesAdapter
    private lateinit var binding: ActivitySearchPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        prepareBinding()
        configUI()
        configObserving()
        //
    }

    private fun prepareBinding() {
        binding = DataBindingUtil.setContentView<ActivitySearchPlaceBinding>(
            this,
            R.layout.activity_search_place
        ).apply {
            lifecycleOwner = this@SearchPlacesActivity
            viewModel = this@SearchPlacesActivity.viewModel
        }

        viewModel.navigator = this
    }

    private fun configObserving() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            val placeType = viewModel.placeTypeText.value as String

            if (placeType.length < 3) {
                return@setOnEditorActionListener true
            }
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getPlaces(placeType)
            }
            true
        }

        viewModel.placesResponse.observe(this, Observer {
            // now update list
            adapter.update(it.results)
        })
    }

    private fun configUI() {
        adapter = binding.recyclerPrediction.bindAdapter(emptyList())
        adapter.itemClickListener = object : RecyclerPlacesAdapter.ItemClickListener {
            override fun onClickItem(place: Place) {
                navigateMapActivity(place)
            }
        }
    }

    override fun getViewModel(): SearchPlacesViewModel {
        viewModel = ViewModelProvider(this, factory).get(SearchPlacesViewModel::class.java)
        return viewModel
    }

    override fun navigateMapActivity(place: Place) {
        startThis(MapActivity::class.java, KEY_INTENT_PLACES, intentObject = place)
    }

}
