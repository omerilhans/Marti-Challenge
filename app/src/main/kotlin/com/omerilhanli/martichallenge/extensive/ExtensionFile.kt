package com.omerilhanli.martichallenge.extensive

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.BuildConfig
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter
import java.io.Serializable

fun RecyclerView.bindAdapter(placeList: List<Place>): RecyclerPlacesAdapter {
    val adapter = RecyclerPlacesAdapter(placeList)
    this.adapter = adapter

    return adapter
}

fun Activity.startThis(activityClass: Class<*>) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
    finish()
}

fun <T : Serializable> Activity.startThis(
    activityClass: Class<*>, intentKey: String = "",
    intentValue: String = "", intentObject: T? = null,
    isFinish: Boolean = false
) {
    val intent = Intent(this, activityClass)
    if (intentValue.isNotEmpty()) {
        intent.putExtra(intentKey, intentValue)
    } else if (intentObject != null) {
        intent.putExtra(intentKey, intentObject)
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