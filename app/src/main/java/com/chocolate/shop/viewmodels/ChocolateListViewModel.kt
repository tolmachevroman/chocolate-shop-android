package com.chocolate.shop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.chocolate.shop.GetProductsQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChocolateListViewModel @Inject constructor() : ViewModel() {


    init {
        val apolloClient = ApolloClient.builder()
            .serverUrl("http://10.0.2.2:7600/graphql")
            .build()

        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            val data = apolloClient.query(GetProductsQuery()).await().data
            data?.products?.apply {
                println("Received $this")
                chocolates.value = this
            }

        }
    }

    private val chocolates: MutableLiveData<List<GetProductsQuery.Product>> by lazy {
        MutableLiveData<List<GetProductsQuery.Product>>().also {
            it.value = listOf()
        }
    }

    fun getChocolates(): LiveData<List<GetProductsQuery.Product>> {
        return chocolates;
    }

//    suspend fun retrieveData(): GetProductsQuery.Data? {
//        return apolloClient.query(GetProductsQuery()).await().data
//    }

//    curl -X POST http://localhost:7600/graphql \
//    -H "Content-Type: application/json" \
//    -d '{"query": "{ products { id, name, description, price, chocolateType, fillings, images } }"}'
}