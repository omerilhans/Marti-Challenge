package com.omerilhanli.martichallenge.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.ktx_common.extensive.CHALLENGE_GITHUB_LINK
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.databinding.ActivityMainBinding
import com.omerilhanli.martichallenge.extensive.openUrlInTabBrowser
import com.omerilhanli.martichallenge.ui.base.BaseActivity
import com.omerilhanli.martichallenge.ui.main.fragment.DetailFragment
import com.omerilhanli.martichallenge.ui.main.fragment.MapFragment
import com.omerilhanli.martichallenge.ui.main.fragment.SearchPlacesFragment
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), MainNavigator {

    private lateinit var mSelectedPlace: Place // seçilen place fragmentler arası paylaşılır

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                handler = this@MainActivity.viewModel
            }

        viewModel.navigator = this
    }

    override fun onBackPressed() { // 3 fragment için ilgili back işlemi gerçekleştirilir
        supportFragmentManager.fragments.forEachIndexed { _, fragment ->
            when (fragment) {
                is MapFragment -> {
                    showSearchPlacesFragment()
                    return@forEachIndexed
                }
                is DetailFragment -> {
                    showMapFragment(mSelectedPlace)
                    return@forEachIndexed
                }
                is SearchPlacesFragment -> {
                    super.onBackPressed()
                    return@forEachIndexed
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_link, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_link -> openUrlInTabBrowser(CHALLENGE_GITHUB_LINK)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showSearchPlacesFragment() {
        replaceFragment(R.id.frame_container, SearchPlacesFragment.newInstance(), SearchPlacesFragment::class.java.simpleName)
    }

    override fun showMapFragment(place: Place) {
        this.mSelectedPlace = place
        replaceFragment(R.id.frame_container, MapFragment.newInstance(place), MapFragment::class.java.simpleName)
    }

    override fun showPlaceDetailFragment(placeDetailResponse: PlaceDetailResponse) {
        replaceFragment(R.id.frame_container, DetailFragment.newInstance(placeDetailResponse), DetailFragment::class.java.simpleName)
    }
}
