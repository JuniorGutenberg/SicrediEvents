package com.orbital.core.data.base

sealed class DataState<out R> {

    data class Success<out T>(val data:T): DataState<T>()

    data class Error(val exception: Throwable): DataState<Nothing>()
}