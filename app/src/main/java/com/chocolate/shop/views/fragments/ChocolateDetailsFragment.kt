package com.chocolate.shop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chocolate.shop.GetProductQuery
import com.chocolate.shop.R
import com.chocolate.shop.databinding.ChocolateDetailsFragmentBinding
import com.chocolate.shop.type.ChocolateType
import com.chocolate.shop.utils.ResourceObserver
import com.chocolate.shop.viewmodels.ChocolateDetailsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChocolateDetailsFragment : Fragment() {
    private val viewModel by viewModels<ChocolateDetailsViewModel>()

    private lateinit var binding: ChocolateDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChocolateDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.setOnMenuItemClickListener { item ->
           if (item.itemId == R.id.delete) {
                
           }
           true
        }

        arguments?.apply {
            val productId: String = ChocolateDetailsFragmentArgs.fromBundle(this).productId
            val productName: String = ChocolateDetailsFragmentArgs.fromBundle(this).productName

            binding.toolbar.title = productName
            viewModel.chocolate(productId).observe(
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

    private fun onSuccess(data: GetProductQuery.Product?) {
        data?.apply {
            Picasso.get()
                .load(images[0])
                .into(binding.image);
            binding.name.text = name
            binding.description.text = formatDescription(description)
            binding.chocolateType.text = formatChocolateType(chocolateType)
            binding.fillings.text = formatFillings(fillings)
            binding.price.text = formatPrice(price)

            binding.updatePrice.visibility = View.VISIBLE
            binding.updatePrice.setOnClickListener {
                val directions =
                    ChocolateDetailsFragmentDirections.navigateToPriceNumberPicker(
                        id.toString(),
                        price
                    )
                findNavController().navigate(directions)
            }
        }
    }

    private fun formatDescription(description: String): String {
        return "\u2022 $description"
    }

    private fun formatChocolateType(chocolateType: ChocolateType): String {
        return "\u2022 " + chocolateType.toString().lowercase()
            .replaceFirstChar { it.uppercase() } + " chocolate"
    }

    private fun formatFillings(fillings: List<String>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("\u2022 ")
        stringBuilder.append("Contains ")
        for ((i, f) in fillings.withIndex()) {
            stringBuilder.append(f.lowercase())
            if (i < fillings.size - 1) stringBuilder.append(", ")
        }

        return stringBuilder.toString()
    }

    private fun formatPrice(price: Int): String {
        return "\u2022 $$price per kilo"
    }

    private fun onError(error: String?) {}
}