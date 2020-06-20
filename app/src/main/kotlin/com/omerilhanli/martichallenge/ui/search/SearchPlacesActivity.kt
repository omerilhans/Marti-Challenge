package com.omerilhanli.martichallenge.ui.search

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivitySearchPlaceBinding
import com.omerilhanli.martichallenge.extensive.*
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import com.omerilhanli.martichallenge.ui.map.MapActivity
import com.omerilhanli.martichallenge.utils.PermissionUtil
import javax.inject.Inject

class SearchPlacesActivity : BaseActivity<SearchPlacesViewModel>(), SearchPlacesNavigator {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchPlacesViewModel

    private lateinit var adapter: RecyclerPlacesAdapter
    private lateinit var binding: ActivitySearchPlaceBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (!PermissionUtil.checkPermission(this)) {
            PermissionUtil.requestPermission(this)
        } else {
            // Permission was granted. (SUCCESS)
            getFindLastLocation()
        }

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

    private fun getFindLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            mLocation = location
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == KEY_PERMISSIONS_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                (grantResults[1] == PackageManager.PERMISSION_GRANTED) &&
                (grantResults[2] == PackageManager.PERMISSION_GRANTED)
            ) {
                // Permission was granted. (SUCCESS)
                getFindLastLocation()
            } else {
                // Permission denied.
                Snackbar.make(
                    currentFocus!!,
                    R.string.permission_denied_explanation,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Ayarlar'a giderek izin verebilirsiniz") {
                    // Build intent that displays the App settings screen.
                    openSetting()
                }.show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun getViewModel(): SearchPlacesViewModel {
        viewModel = ViewModelProvider(this, factory).get(SearchPlacesViewModel::class.java)
        return viewModel
    }

    override fun navigateMapActivity(place: Place) {
        startThis(MapActivity::class.java, KEY_INTENT_PLACES, intentObject = place)
    }

}
