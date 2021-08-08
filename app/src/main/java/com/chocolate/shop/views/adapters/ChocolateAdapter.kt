package com.chocolate.shop.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chocolate.shop.GetProductsQuery
import com.chocolate.shop.R
import com.chocolate.shop.databinding.ChocolateAdapterItemBinding
import com.squareup.picasso.Picasso
import java.util.*

class ChocolateAdapter(private val data: List<GetProductsQuery.Product>) :
    RecyclerView.Adapter<ChocolateAdapter.ViewHolder>() {

    private val rand = Random()

    class ViewHolder(binding: ChocolateAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val item = binding.item
        val textView = binding.label
        val image = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChocolateAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position].name
//        holder.image.layoutParams.height = getRandomIntInRange(350, 250)
        Picasso.get()
            .load(data[position].images[0])
            .into(holder.image);
        holder.item.setOnClickListener {
            println("Click!")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getRandomIntInRange(max: Int, min: Int): Int {
        return rand.nextInt(max - min + min) + min
    }
}

