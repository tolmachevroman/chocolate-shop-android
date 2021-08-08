package com.chocolate.shop.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chocolate.shop.R
import com.chocolate.shop.databinding.ActivityChocolateListBinding
import com.chocolate.shop.viewmodels.ChocolateListViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.chocolate.shop.views.adapters.ChocolateAdapter
import com.chocolate.shop.views.adapters.StaggeredGridBorderDecoration

@AndroidEntryPoint
class ChocolateListActivity : AppCompatActivity() {
    private val viewModel by viewModels<ChocolateListViewModel>()

    private lateinit var binding: ActivityChocolateListBinding
    private lateinit var adapter: ChocolateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChocolateListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //set layout manager
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(StaggeredGridBorderDecoration(8,
            ColorDrawable(Color.parseColor("#ffffff"))))

        viewModel.getChocolates().observe(this, Observer { data ->
            println("Got: $data");
            if (data.isNotEmpty()) {
                adapter = ChocolateAdapter(data)
                binding.recyclerView.adapter = adapter
            }
        })

    }
}