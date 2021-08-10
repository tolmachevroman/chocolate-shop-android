package com.chocolate.shop.views.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chocolate.shop.GetProductsQuery
import com.chocolate.shop.databinding.ChocolateListFragmentBinding
import com.chocolate.shop.utils.ResourceObserver
import com.chocolate.shop.viewmodels.ChocolateListViewModel
import com.chocolate.shop.views.adapters.ChocolateAdapter
import com.chocolate.shop.views.adapters.StaggeredGridBorderDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChocolateListFragment : Fragment() {

    private val viewModel by viewModels<ChocolateListViewModel>()

    private lateinit var binding: ChocolateListFragmentBinding
    private lateinit var adapter: ChocolateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChocolateListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.title = "Chocolate Shop"

        //set layout manager
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(
            StaggeredGridBorderDecoration(
                8,
                ColorDrawable(Color.parseColor("#ffffff"))
            )
        )

        binding.fab.setOnClickListener {

        }

        viewModel.chocolates().observe(
            viewLifecycleOwner, ResourceObserver(
                javaClass.simpleName,
                ::hideLoading,
                ::showLoading,
                ::onSuccess,
                ::onError
            )
        )
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onSuccess(data: List<GetProductsQuery.Product>?) {
        if (!data.isNullOrEmpty()) {
            adapter = ChocolateAdapter(data) { productId, productName ->
                val directions = ChocolateListFragmentDirections.navigateToChocolateDetails(productId, productName)
                findNavController().navigate(directions)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    private fun onError(error: String?) {}
}