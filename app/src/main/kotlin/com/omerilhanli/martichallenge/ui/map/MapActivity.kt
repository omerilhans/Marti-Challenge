package com.omerilhanli.martichallenge.ui.map

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivityMapBinding
import com.omerilhanli.martichallenge.extensive.KEY_INTENT_PLACES
import com.omerilhanli.martichallenge.extensive.KEY_INTENT_PLACE_DETAIL
import com.omerilhanli.martichallenge.extensive.startThis
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import com.omerilhanli.martichallenge.ui.detail.DetailActivity
import javax.inject.Inject

class MapActivity : BaseActivity<MapViewModel>(), MapNavigator, OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MapViewModel

    private lateinit var googleMap: GoogleMap

    private lateinit var place: Place
    private lateinit var placeDetailResponse: PlaceDetailResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configBinding()
        //
        extractDataFromIntent()
        //
        configObserving()
    }

    private fun configBinding() {
        val binding =
            DataBindingUtil.setContentView<ActivityMapBinding>(this, R.layout.activity_map)
                .apply {
                    lifecycleOwner = this@MapActivity
                    viewModel = this@MapActivity.viewModel
                    handler = this@MapActivity.viewModel
                }
        viewModel.navigator = this

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun extractDataFromIntent() { // // //
        place = intent?.extras?.getSerializable(KEY_INTENT_PLACES) as Place
    }

    private fun configObserving() {

        viewModel.getPlaceDetails(place.place_id)

        viewModel.placeDetail.observe(this, Observer {

            with(it) {
                placeDetailResponse = this
                val latLng = LatLng(result.geometry.location.lat, result.geometry.location.lng)
                addMarkerAndMoveCamera(latLng)
            }
        })
    }

    private fun animateCamera(latLng: LatLng) {
        googleMap.animateCamera(createCameraPosition(latLng))
    }

    private fun createCameraPosition(latLng: LatLng): CameraUpdate {
        return CameraUpdateFactory.newCameraPosition(
            CameraPosition.Builder().target(latLng).zoom(
                16.0f
            ).build()
        )
    }

    private fun addMarkerAndMoveCamera(latLng: LatLng) {
        animateCamera(latLng)
        googleMap.addMarker(MarkerOptions().position(latLng))
        this.googleMap.moveCamera(createCameraPosition(latLng))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.googleMap.setOnMarkerClickListener(this)
    }

    override fun getViewModel(): MapViewModel {
        viewModel = ViewModelProvider(this, factory).get(MapViewModel::class.java)
        return viewModel
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        startThis(
            DetailActivity::class.java,
            KEY_INTENT_PLACE_DETAIL,
            intentObject = placeDetailResponse
        )
        return true
    }
}
