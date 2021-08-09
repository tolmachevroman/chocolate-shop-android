package com.chocolate.shop.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chocolate.shop.GetProductsQuery
import com.chocolate.shop.databinding.ActivityChocolateListBinding
import com.chocolate.shop.utils.ResourceObserver
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
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(
            StaggeredGridBorderDecoration(
                8,
                ColorDrawable(Color.parseColor("#ffffff"))
            )
        )

        viewModel.chocolates().observe(
            this, ResourceObserver(
                javaClass.simpleName,
                ::hideLoading,
                ::showLoading,
                ::onSuccess,
                ::onError
            )
        )
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun onSuccess(data: List<GetProductsQuery.Product>?) {
        if (!data.isNullOrEmpty()) {
            adapter = ChocolateAdapter(data)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun onError(error: String?) {}
}