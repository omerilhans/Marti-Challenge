package com.omerilhanli.martichallenge.extensive

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.BuildConfig
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter

fun RecyclerView.bindAdapter(placeList: List<Place>): RecyclerPlacesAdapter {
    val adapter = RecyclerPlacesAdapter(placeList)
    this.adapter = adapter

    return adapter
}

fun Activity.startThis(
    activityClass: Class<*>, intentKey: String = "",
    intentValue: String = "", isFinish: Boolean = false
) {
    val intent = Intent(this, activityClass)
    if (intentValue.isNotEmpty()) {
        intent.putExtra(intentKey, intentValue)
    }
    startActivity(intent)
    if (isFinish)
        finish()
}

fun Activity.openSetting() {
    // Build intent that displays the App settings screen.
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
    intent.data = uri
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

fun Context.openUrlInTabBrowser(url: String?) {
    url?.let {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}

fun Context.hideKeyboardFrom(view: View) {
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}