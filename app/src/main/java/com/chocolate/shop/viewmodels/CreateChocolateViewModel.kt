package com.chocolate.shop.viewmodels

import androidx.lifecycle.*
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.chocolate.shop.CreateProductMutation
import com.chocolate.shop.data.ChocolateRepository
import com.chocolate.shop.type.ChocolateType
import com.chocolate.shop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateChocolateViewModel @Inject constructor(private val repository: ChocolateRepository) :
    ViewModel() {

    fun createChocolate(
        name: String, description: String, price: Int, chocolateType: ChocolateType,
        fillings: List<String>, images: List<String>
    ): LiveData<Resource<CreateProductMutation.Data>> = liveData {
        emit(Resource.loading(null))

        var errorMessage: String? = null
        val response = try {
            repository.createProduct(name, description, price, chocolateType, fillings, images)
                .await()
        } catch (e: ApolloException) {
            errorMessage = e.toString()
            null
        }

        val data = response?.data
        if (response != null && !response.hasErrors()) {
            emit(Resource.success(data))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }
}