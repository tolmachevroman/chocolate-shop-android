package com.chocolate.shop.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chocolate.shop.databinding.ActivityChocolateDetailsBinding

class ChocolateDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChocolateDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChocolateDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = "Chocolate #id"

    }
}