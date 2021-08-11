package com.chocolate.shop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chocolate.shop.CreateProductMutation
import com.chocolate.shop.GetProductQuery
import com.chocolate.shop.R
import com.chocolate.shop.databinding.CreateChocolateFragmentBinding
import com.chocolate.shop.type.ChocolateType
import com.chocolate.shop.utils.ResourceObserver
import com.chocolate.shop.viewmodels.CreateChocolateViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateChocolateFragment : Fragment() {
    private val viewModel by viewModels<CreateChocolateViewModel>()

    private lateinit var binding: CreateChocolateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateChocolateFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.title = "Add product"

        //TODO remove pre filled data
        binding.name.setText("Senegal")
        binding.description.setText("Amazing chocolate produced using traditional recipes and the best organic cacao beans on the market. Tender aroma and rich flavour makes this chocolate an excellent choice. Designed and made in a facility that handles gluten, nuts and dairy.")
        binding.price.setText("95")
        binding.fillings.setText("Raspberry")
        binding.images.setText("https://cdn.shopify.com/s/files/1/0205/2710/products/067_SocolaChocolates081416.jpg?v=1472793583")

        binding.createProductButton.setOnClickListener {
            viewModel.createChocolate(
                binding.name.text.toString(),
                binding.description.text.toString(),
                binding.price.text.toString().toInt(),
                ChocolateType.MILK,
                listOf(binding.fillings.text.toString()),
                listOf(binding.images.text.toString())
            ).observe(
                viewLifecycleOwner, ResourceObserver(
                    javaClass.simpleName,
                    ::hideLoading,
                    ::showLoading,
                    ::onSuccess,
                    ::onError
                )
            )
        }
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onSuccess(data: CreateProductMutation.Data?) {
        val directions = CreateChocolateFragmentDirections.navigateToChocolateList()
        findNavController().navigate(directions)
    }


    private fun onError(error: String?) {}
}