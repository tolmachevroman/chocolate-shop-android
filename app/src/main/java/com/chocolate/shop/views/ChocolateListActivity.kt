package com.chocolate.shop.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.chocolate.shop.R
import com.chocolate.shop.viewmodels.ChocolateListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider

@AndroidEntryPoint
class ChocolateListActivity : AppCompatActivity() {
    private val viewModel by viewModels<ChocolateListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getChocolates().observe(this, Observer { data ->
            println("Got: $data");
        })

    }
}