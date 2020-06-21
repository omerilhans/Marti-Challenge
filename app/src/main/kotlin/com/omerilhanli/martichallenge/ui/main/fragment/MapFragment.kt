package com.omerilhanli.martichallenge.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.ktx_common.extensive.KEY_INTENT_PLACE
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.FragmentMapBinding
import com.omerilhanli.martichallenge.ui.base.BaseFragment
import com.omerilhanli.martichallenge.ui.main.MainActivity
import com.omerilhanli.martichallenge.ui.main.MainViewModel

class MapFragment : BaseFragment<MainViewModel>(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    private lateinit var binding: FragmentMapBinding

    private var googleMap: GoogleMap? = null
    private lateinit var place: Place
    private lateinit var placeDetailResponse: PlaceDetailResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentMapBinding>(inflater, R.layout.fragment_map, container, false)
            .apply {
                lifecycleOwner = this@MapFragment
                viewModel = this@MapFragment.viewModel
                handler = this@MapFragment.viewModel
            }

        viewModel.navigator = activity as MainActivity

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configBinding()
        extractDataFromIntent()
        configObserving()
    }

    private fun configBinding() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun extractDataFromIntent() { // // //
        place = arguments?.getSerializable(KEY_INTENT_PLACE) as Place
    }

    private fun configObserving() {

        viewModel.getPlaceDetails(place.place_id)

        viewModel.placeDetail.observe(viewLifecycleOwner, Observer {

            with(it) {
                placeDetailResponse = this
                val latLng = LatLng(result?.geometry?.location?.lat ?: 0.0, result?.geometry?.location?.lng ?: 0.0)
                addMarkerAndMoveCamera(latLng)
            }
        })
    }

    private fun animateCamera(latLng: LatLng) {
        googleMap?.animateCamera(createCameraPosition(latLng))
    }

    private fun createCameraPosition(latLng: LatLng): CameraUpdate {
        return CameraUpdateFactory.newCameraPosition(
            CameraPosition.Builder()
                .target(latLng)
                .zoom(16.0f)
                .build()
        )
    }

    private fun addMarkerAndMoveCamera(latLng: LatLng) {
        animateCamera(latLng)
        googleMap?.addMarker(MarkerOptions().position(latLng).title(getString(R.string.Generic_title)))
        this.googleMap?.moveCamera(createCameraPosition(latLng))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.googleMap?.setOnMarkerClickListener(this)
        this.googleMap?.uiSettings?.isCompassEnabled = true
        this.googleMap?.uiSettings?.isZoomControlsEnabled = true
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.navigator?.showPlaceDetailFragment(placeDetailResponse)
        return true
    }

    companion object {

        fun newInstance(place: Place): MapFragment {
            val fragment = MapFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_INTENT_PLACE, place)
            fragment.arguments = bundle
            return fragment
        }
    }
}