package com.chocolate.shop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chocolate.shop.databinding.ChocolatePriceNumberPickerFragmentBinding
import com.chocolate.shop.utils.ResourceObserver
import com.chocolate.shop.viewmodels.PriceNumberPickerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceNumberPickerFragment : BottomSheetDialogFragment() {
    private val viewModel by viewModels<PriceNumberPickerViewModel>()

    private lateinit var binding: ChocolatePriceNumberPickerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChocolatePriceNumberPickerFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.apply {
            val productId: String = PriceNumberPickerFragmentArgs.fromBundle(this).productId
            val currentPrice: Int = PriceNumberPickerFragmentArgs.fromBundle(this).currentPrice

            binding.numberPicker.minValue = (currentPrice * 0.5).toInt()
            binding.numberPicker.maxValue = (currentPrice * 1.5).toInt()
            binding.numberPicker.value = currentPrice

            binding.okButton.setOnClickListener {
                viewModel.updateChocolatePrice(productId, binding.numberPicker.value).observe(
                    viewLifecycleOwner, ResourceObserver(
                        javaClass.simpleName,
                        hideLoading = {},
                        showLoading = {},
                        ::onSuccess,
                        ::onError
                    )
                )
            }
        }
    }

    private fun onSuccess(updated: Boolean) {
        dismissAllowingStateLoss()
    }

    private fun onError(error: String?) {}
}