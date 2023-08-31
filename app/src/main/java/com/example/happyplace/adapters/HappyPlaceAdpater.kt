package com.example.happyplace.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplace.databinding.ItemHappyPlaceBinding
import com.example.happyplace.model.HappyPlaceModel

class HappyPlaceAdapter(private val context: Context, private var list: ArrayList<HappyPlaceModel>) :
    RecyclerView.Adapter<HappyPlaceAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHappyPlaceBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(private val binding: ItemHappyPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: HappyPlaceModel) {
            binding.ivPlaceImage.setImageURI(Uri.parse(model.image))
            binding.tvTitle.text = model.title
            binding.tvDescription.text = model.description
        }
    }
}
