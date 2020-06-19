package com.omerilhanli.martichallenge.extensive

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter
import java.io.Serializable

fun RecyclerView.bindAdapter(placeList: List<Place>): RecyclerPlacesAdapter {
    val adapter = RecyclerPlacesAdapter(placeList)
    this.adapter = adapter

    return adapter
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