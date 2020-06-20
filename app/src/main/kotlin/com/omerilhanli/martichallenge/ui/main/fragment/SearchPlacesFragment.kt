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
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.ktx_common.KEY_PERMISSIONS_REQUEST_CODE
import com.omerilhanli.ktx_common.PermissionUtil
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.FragmentSearchPlacesBinding
import com.omerilhanli.martichallenge.extensive.bindAdapter
import com.omerilhanli.martichallenge.extensive.openSetting
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter
import com.omerilhanli.martichallenge.ui.base.BaseFragment
import com.omerilhanli.martichallenge.ui.main.MainViewModel
import javax.inject.Inject

class SearchPlacesFragment : BaseFragment<MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentSearchPlacesBinding
    private lateinit var adapter: RecyclerPlacesAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mLocation: Location? = null

    override fun getViewModel(): MainViewModel {
        viewModel = activity?.run {
            ViewModelProvider(
                activity!!,
                factory
            ).get(MainViewModel::class.java)
        }
            ?: throw Exception("Invalid Activity")
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchPlacesBinding>(
            inflater,
            R.layout.fragment_search_places,
            container,
            false
        ).apply {
            lifecycleOwner = this@SearchPlacesFragment
            viewModel = this@SearchPlacesFragment.viewModel
            handler = this@SearchPlacesFragment.viewModel
        }

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
        adapter = binding.recyclerPrediction.bindAdapter(emptyList())
        adapter.itemClickListener = object : RecyclerPlacesAdapter.ItemClickListener {
            override fun onClickItem(place: Place) {
                viewModel.navigator?.showMapFragment(place)
            }
        }
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

        viewModel.placesResponse.observe(viewLifecycleOwner, Observer {
            // now update list
            adapter.update(it.results)
        })
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
                    view!!,
                    "R.string.permission_denied_explanation",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Ayarlar'a giderek izin verebilirsiniz") {
                    // Build intent that displays the App settings screen.
                    activity?.openSetting()
                }.show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val TAG: String = "SearchPlacesFragment"

        fun newInstance(): SearchPlacesFragment {
            return SearchPlacesFragment()
        }
    }
}