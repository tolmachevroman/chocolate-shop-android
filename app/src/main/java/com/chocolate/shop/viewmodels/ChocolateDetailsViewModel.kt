package com.chocolate.shop.viewmodels

import androidx.lifecycle.*
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
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

        var errorMessage: String? = null
        val response = try {
            repository.product(id).await()
        } catch (e: ApolloException) {
            errorMessage = e.toString()
            null
        }

        val data = response?.data
        if (response != null && !response.hasErrors()) {
            emit(Resource.success(data?.product))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }

    fun updateChocolatePrice(id: String, newPrice: Int): LiveData<Resource<Boolean>> = liveData {
        emit(Resource.loading(null))

        var errorMessage: String? = null
        val response = try {
            repository.updateProductPrice(id, newPrice).await()
        } catch (e: ApolloException) {
            errorMessage = e.toString()
            null
        }

        val data = response?.data
        if (response != null && !response.hasErrors()) {
            emit(Resource.success(data?.updateProductPrice))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }
}