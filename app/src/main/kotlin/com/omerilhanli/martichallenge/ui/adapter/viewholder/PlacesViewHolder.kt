package com.omerilhanli.martichallenge.ui.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.BR
import com.omerilhanli.martichallenge.ui.adapter.RecyclerPlacesAdapter

class PlacesViewHolder(
    private val binding: ViewDataBinding,
    private val itemClickListener: RecyclerPlacesAdapter.ItemClickListener? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(place: Place) {
        binding.setVariable(BR.place, place)
        binding.setVariable(BR.itemClickListener, itemClickListener)
        binding.executePendingBindings()
    }
}