package com.chocolate.shop.utils

import android.util.Log
import androidx.lifecycle.Observer

class ResourceObserver<T>(private val tag: String,
                          private val hideLoading: () -> Unit,
                          private val showLoading: () -> Unit,
                          private val onSuccess: (data: T) -> Unit,
                          private val onError: (message: String?) -> Unit) : Observer<Resource<T>> {

    override fun onChanged(resource: Resource<T>?) {
        when (resource?.status) {
            Resource.Status.SUCCESS -> {
                hideLoading()
                if (resource.data != null) {
                    Log.d(tag, "observer -> SUCCESS, ${resource.data} items")
                    onSuccess(resource.data)
                }
            }
            Resource.Status.ERROR -> {
                hideLoading()
                if (resource.error != null) {
                    Log.d(tag, "observer -> ERROR, ${resource.error}")
                    onError(resource.error.message)
                }
            }
            Resource.Status.LOADING -> {
                showLoading()
                Log.d(tag, "observer -> LOADING")
            }
        }
    }
}