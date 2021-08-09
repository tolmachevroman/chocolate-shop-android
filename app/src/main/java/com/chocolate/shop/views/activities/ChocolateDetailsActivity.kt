package com.chocolate.shop.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chocolate.shop.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChocolateDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chocolate_list_host)
    }

}