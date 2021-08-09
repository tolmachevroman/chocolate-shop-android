package com.chocolate.shop.views.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chocolate.shop.GetProductsQuery
import com.chocolate.shop.databinding.ChocolateAdapterItemBinding
import com.squareup.picasso.Picasso

class ChocolateAdapter(private val data: List<GetProductsQuery.Product>, private val onClickListener: ((Int) -> Unit) ) :
    RecyclerView.Adapter<ChocolateAdapter.ViewHolder>() {

    private var heights: List<Int>
    init {
        val h = mutableListOf<Int>()
        val screenHeight = Resources.getSystem().displayMetrics.run { heightPixels }
        for (i in 0..data.size) {
            val percentage = (30..50).random() / 100.0
            h.add((screenHeight * percentage).toInt())
        }
        heights = h
    }

    class ViewHolder(binding: ChocolateAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val item = binding.item
        val textView = binding.name
        val image = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChocolateAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position].name
        holder.image.layoutParams.height = heights[position]
        Picasso.get()
            .load(data[position].images[0])
            .into(holder.image);
        holder.item.setOnClickListener {
            onClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

