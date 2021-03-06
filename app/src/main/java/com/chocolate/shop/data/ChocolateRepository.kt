package com.chocolate.shop.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloMutationCall
import com.apollographql.apollo.ApolloQueryCall
import com.chocolate.shop.*
import com.chocolate.shop.type.ChocolateType
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

    fun updateProductPrice(
        id: String,
        newPrice: Int
    ): ApolloMutationCall<UpdateProductPriceMutation.Data> {
        return apolloClient.mutate(UpdateProductPriceMutation(id, newPrice))
    }

    fun createProduct(
        name: String, description: String, price: Int, chocolateType: ChocolateType,
        fillings: List<String>, images: List<String>
    ): ApolloMutationCall<CreateProductMutation.Data> {
        return apolloClient.mutate(
            CreateProductMutation(
                name,
                description,
                price,
                chocolateType,
                fillings,
                images
            )
        )
    }

    fun deleteProduct(
        id: String
    ): ApolloMutationCall<DeleteProductMutation.Data> {
        return apolloClient.mutate(DeleteProductMutation(id))
    }
}