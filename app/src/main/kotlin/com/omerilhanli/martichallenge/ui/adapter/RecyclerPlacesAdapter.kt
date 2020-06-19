package com.omerilhanli.martichallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.R
import com.omerilhanli.martichallenge.ui.adapter.viewholder.PlacesViewHolder

class RecyclerPlacesAdapter(var placeList: List<Place>) :
    RecyclerView.Adapter<PlacesViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_places,
            parent,
            false
        )

        return PlacesViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    override fun getItemCount() = placeList.size

    //
    fun clear() {
        this.placeList = emptyList()
        notifyDataSetChanged()
    }

    //
    fun update(places: List<Place>) {
        this.placeList = places
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClickItem(place: Place)
    }
}

