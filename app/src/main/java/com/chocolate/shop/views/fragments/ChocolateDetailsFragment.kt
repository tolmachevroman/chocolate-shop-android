package com.chocolate.shop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chocolate.shop.GetProductQuery
import com.chocolate.shop.databinding.ChocolateDetailsFragmentBinding
import com.chocolate.shop.databinding.ChocolateListFragmentBinding
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
        arguments?.apply {
            val productId: String = ChocolateDetailsFragmentArgs.fromBundle(this).productId

            viewModel.chocolate(productId).observe(
                viewLifecycleOwner, ResourceObserver(
                    javaClass.simpleName,
                    ::hideLoading,
                    ::showLoading,
                    ::onSuccess,
                    ::onError
                )
            )

            //TODO move to another fragment
//            val newPrice = 555
//            viewModel.updateChocolatePrice(productId, newPrice).observe(
//                viewLifecycleOwner, ResourceObserver(
//                    javaClass.simpleName,
//                    ::hideLoading,
//                    ::showLoading,
//                    onSuccess = { binding.price.text = formatPrice(newPrice) },
//                    ::onError
//                )
//            )

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
            binding.description.text = description
            binding.chocolateType.text = formatChocolateType(chocolateType)
            binding.fillings.text = formatFillings(fillings)
            binding.price.text = formatPrice(price)
        }
    }

    private fun formatChocolateType(chocolateType: ChocolateType): String {
        return chocolateType.toString().lowercase()
            .replaceFirstChar { it.uppercase() } + " chocolate"
    }

    private fun formatFillings(fillings: List<String>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Contains ")
        for ((i, f) in fillings.withIndex()) {
            stringBuilder.append(f.lowercase())
            if (i < fillings.size - 1) stringBuilder.append(", ")
        }

        return stringBuilder.toString()
    }

    private fun formatPrice(price: Int): String {
        return "$$price per kilo"
    }

    private fun onError(error: String?) {}
}