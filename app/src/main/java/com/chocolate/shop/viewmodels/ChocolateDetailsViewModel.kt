package com.chocolate.shop.viewmodels

import androidx.lifecycle.*
import com.apollographql.apollo.coroutines.await
import com.chocolate.shop.GetProductQuery
import com.chocolate.shop.UpdateProductPriceMutation
import com.chocolate.shop.data.ChocolateRepository
import com.chocolate.shop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChocolateDetailsViewModel @Inject constructor(private val repository: ChocolateRepository) :
    ViewModel() {

    fun chocolate(id: String): LiveData<Resource<GetProductQuery.Product>> = liveData {
        emit(Resource.loading(null))
        val data = repository.product(id).await().data
        try {
            println("Received ${data?.product}")
            emit(Resource.success(data?.product))
        } catch (e: Exception) {
            emit(Resource.error(data?.product, e.toString()))
        }
    }

    fun updateChocolatePrice(id: String, newPrice: Int): LiveData<Resource<Boolean>> = liveData {
        emit(Resource.loading(null))
        val data = repository.updateProductPrice(id, newPrice).await().data
        try {
            println("Updated the price? ${data?.updateProductPrice}")
            emit(Resource.success(data?.updateProductPrice))
        } catch (e: Exception) {
            emit(Resource.error(null, e.toString()))
        }
    }
}