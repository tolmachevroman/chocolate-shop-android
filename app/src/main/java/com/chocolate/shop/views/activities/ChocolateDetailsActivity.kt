package com.chocolate.shop.views.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chocolate.shop.GetProductQuery
import com.chocolate.shop.databinding.ActivityChocolateDetailsBinding
import com.chocolate.shop.utils.ResourceObserver
import com.chocolate.shop.viewmodels.ChocolateDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChocolateDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModels<ChocolateDetailsViewModel>()

    private lateinit var binding: ActivityChocolateDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChocolateDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = "Chocolate #id"

        viewModel.chocolate("1").observe(
            this, ResourceObserver(
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

    private fun onSuccess(data: GetProductQuery.Product?) {

    }

    private fun onError(error: String?) {}
}