package com.chocolate.shop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChocolateListViewModel @Inject constructor() : ViewModel() {
    private val chocolates: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            it.value = listOf("One", "Two")
        }
    }

    fun getChocolates(): LiveData<List<String>> {
        return chocolates;
    }

}