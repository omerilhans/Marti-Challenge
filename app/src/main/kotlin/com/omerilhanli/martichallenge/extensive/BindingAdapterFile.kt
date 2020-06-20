package com.omerilhanli.martichallenge.extensive

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.omerilhanli.api_ktx.BuildConfig.KEY_API

@BindingAdapter("runStatement")
fun View.runStatement(callFunc: () -> Unit) {
    callFunc()
}

@BindingAdapter("bindRating")
fun RatingBar.bindRating(rating: Double) {
    setRating(rating.toFloat())
}

@BindingAdapter("bindPhotoReference")
fun ImageView.bindPhotoReference(reference: String) {
    val photoUrl = java.lang.String.format(
        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=%s&photoreference=%s&key=%s",
        400,
        reference,
        KEY_API
    )
    Glide.with(context).load(photoUrl).into(this)
}