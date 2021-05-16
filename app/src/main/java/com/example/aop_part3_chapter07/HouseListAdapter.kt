package com.example.aop_part3_chapter07

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HouseListAdapter : ListAdapter<HouseModel, HouseListAdapter.ViewHolder>(differ) {
    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(houseModel : HouseModel) {
            val titleTextView =  view.findViewById<TextView>(R.id.titleTextVIew)
            val priceTextVIew = view.findViewById<TextView>(R.id.priceTextView)
            val thumbnailImageView = view.findViewById<ImageView>(R.id.thumbnailImageView)

            titleTextView.text = houseModel.title
            priceTextVIew.text = houseModel.price
            Glide
                .with(thumbnailImageView.context)
                .load(houseModel.imageUrl)
                .transform(CenterCrop(), RoundedCorners(dpTOPx(thumbnailImageView.context,12)))
                .into(thumbnailImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(inflater.inflate(R.layout.item_house, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private fun dpTOPx(context : Context, dp : Int) : Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
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