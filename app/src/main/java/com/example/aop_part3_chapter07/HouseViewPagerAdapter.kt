package com.example.aop_part3_chapter07

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HouseViewPagerAdapter(val itemClicked : (HouseModel) -> Unit) : ListAdapter<HouseModel, HouseViewPagerAdapter.ViewHolder>(differ) {
    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(houseModel : HouseModel) {
            val titleTextView =  view.findViewById<TextView>(R.id.titleTextVIew)
            val priceTextVIew = view.findViewById<TextView>(R.id.priceTextView)
            val thumbnailImageView = view.findViewById<ImageView>(R.id.thumbnailImageView)

            titleTextView.text = houseModel.title
            priceTextVIew.text = houseModel.price

            view.setOnClickListener {
                itemClicked(houseModel)
            }

            Glide
                .with(thumbnailImageView.context)
                .load(houseModel.imageUrl)
                .into(thumbnailImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(inflater.inflate(R.layout.item_house_for_viewpager, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<HouseModel>() {
            override fun areItemsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}