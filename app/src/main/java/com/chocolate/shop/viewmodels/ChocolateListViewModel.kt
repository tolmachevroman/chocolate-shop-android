package com.chocolate.shop.viewmodels

import androidx.lifecycle.*
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
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

        var errorMessage: String? = null
        val response = try {
            repository.products().await()
        } catch (e: ApolloException) {
            errorMessage = e.toString()
            null
        }

        val data = response?.data
        if (response != null && !response.hasErrors()) {
            emit(Resource.success(data?.products))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }
}