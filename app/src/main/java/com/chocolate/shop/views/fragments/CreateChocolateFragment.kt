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

//        viewModel.chocolate(productId).observe(
//            viewLifecycleOwner, ResourceObserver(
//                javaClass.simpleName,
//                ::hideLoading,
//                ::showLoading,
//                ::onSuccess,
//                ::onError
//            )
//        )
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onSuccess(data: GetProductQuery.Product?) {
        //TODO finish this fragment
    }


    private fun onError(error: String?) {}
}