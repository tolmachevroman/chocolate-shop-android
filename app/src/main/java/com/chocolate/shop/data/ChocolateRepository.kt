package com.chocolate.shop.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.chocolate.shop.GetProductQuery
import com.chocolate.shop.GetProductsQuery
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
/**
 * In real-life application we'd have database and webservice as data sources
 * passed as dependencies in the constructor; for the purpose of showing GraphQL
 * the code below is enough
 */
class ChocolateRepository @Inject constructor() {

    private val apolloClient = ApolloClient.builder()
        .serverUrl("http://10.0.2.2:7600/graphql")
        .build()

    fun products(): ApolloQueryCall<GetProductsQuery.Data> {
        return apolloClient.query(GetProductsQuery())
    }

    fun product(id: String): ApolloQueryCall<GetProductQuery.Data> {
        return apolloClient.query(GetProductQuery(id))
    }
}