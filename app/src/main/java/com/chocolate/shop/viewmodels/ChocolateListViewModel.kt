package com.chocolate.shop.viewmodels

import androidx.lifecycle.*
import com.apollographql.apollo.coroutines.await
import com.chocolate.shop.GetProductsQuery
import com.chocolate.shop.data.ChocolateRepository
import com.chocolate.shop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChocolateListViewModel @Inject constructor(private val repository: ChocolateRepository) :
    ViewModel() {

    fun chocolates(): LiveData<Resource<List<GetProductsQuery.Product>>> = liveData {
        emit(Resource.loading(listOf()))
        val data = repository.products().await().data
        try {
            println("Received ${data?.products}")
            emit(Resource.success(data?.products))
        } catch (e: Exception) {
            emit(Resource.error(data?.products, e.toString()))
        }
    }
}