package com.chocolate.shop.utils

class Resource<out T> constructor(val status: Status, val data: T?, val error: String?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(data: T?, error: String?): Resource<T> = Resource(Status.ERROR, data, error)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }

    override fun toString(): String =
        status.toString() + ", data:" + data?.toString() + ", error:" + error?.toString()
}