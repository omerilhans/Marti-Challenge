package com.omerilhanli.martichallenge.ui.main.fragment

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.ktx_common.extensive.KEY_PERMISSIONS_REQUEST_CODE
import com.omerilhanli.ktx_common.util.PermissionUtil
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.FragmentSearchPlacesBinding
import com.omerilhanli.martichallenge.extensive.bindAdapter
import com.omerilhanli.martichallenge.extensive.hideKeyboardFrom
import com.omerilhanli.martichallenge.extensive.openSetting
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter
import com.omerilhanli.martichallenge.ui.base.BaseFragment
import com.omerilhanli.martichallenge.ui.main.MainActivity
import com.omerilhanli.martichallenge.ui.main.MainViewModel

class SearchPlacesFragment : BaseFragment<MainViewModel>() {

    private lateinit var binding: FragmentSearchPlacesBinding
    private lateinit var adapter: RecyclerPlacesAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mLocation: Location? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentSearchPlacesBinding>(inflater, R.layout.fragment_search_places, container, false)
            .apply {
                lifecycleOwner = this@SearchPlacesFragment
                viewModel = this@SearchPlacesFragment.viewModel
                handler = this@SearchPlacesFragment.viewModel
            }

        viewModel.navigator = activity as MainActivity

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (!PermissionUtil.checkPermission(requireActivity())) {
            PermissionUtil.requestPermission(requireActivity())
        } else {
            // Permission was granted. (SUCCESS)
            getFindLastLocation()
        }

        //
        configUI()
        configObserving()
        //
    }

    private fun configUI() {
        adapter = binding.recyclerPlaces.bindAdapter(emptyList())
        adapter.itemClickListener = object : RecyclerPlacesAdapter.ItemClickListener {
            override fun onClickItem(place: Place) {
                viewModel.navigator?.showMapFragment(place)
            }
        }
    }

    private fun configObserving() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            val placeType = viewModel.placeTypeText.value as String
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val locationStr = "${mLocation?.latitude},${mLocation?.longitude}"
                viewModel.getPlaces(placeType, locationStr)
                context?.hideKeyboardFrom(view!!)
            }
            true
        }

        viewModel.placesResponse.observe(viewLifecycleOwner, Observer {
            // now update list
            adapter.update(it.results)
        })
    }

    private fun getFindLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            mLocation = location
            binding.locationStr = "${mLocation?.latitude},${mLocation?.longitude}"
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == KEY_PERMISSIONS_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                (grantResults[1] == PackageManager.PERMISSION_GRANTED)
            ) {
                // Permission was granted. (SUCCESS)
                getFindLastLocation()

            } else {
                // Permission denied.
                Snackbar.make(view!!, R.string.Permission_denied_explanation, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.Generic_yes) {
                        activity?.openSetting()
                    }.show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {

        fun newInstance(): SearchPlacesFragment {
            return SearchPlacesFragment()
        }
    }
}