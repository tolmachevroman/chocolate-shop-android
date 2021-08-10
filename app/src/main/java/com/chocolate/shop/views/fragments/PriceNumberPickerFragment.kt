package com.chocolate.shop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chocolate.shop.databinding.ChocolatePriceNumberPickerFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PriceNumberPickerFragment : BottomSheetDialogFragment() {

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
        }
    }
}