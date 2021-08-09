package com.chocolate.shop.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chocolate.shop.R
import com.chocolate.shop.views.fragments.ChocolateListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChocolateListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chocolate_list_host)

        if (savedInstanceState == null) {
            val fragment = ChocolateListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_content, fragment)
                .commit()
        }
    }


}