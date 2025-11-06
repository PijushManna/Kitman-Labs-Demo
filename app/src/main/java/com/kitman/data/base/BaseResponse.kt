package com.kitman.data.base

sealed class BaseResponse<out T> {
    data class Success<T>(val data: T) : BaseResponse<T>()
    data class Error(val message: String) : BaseResponse<Nothing>()
    data object Loading : BaseResponse<Nothing>()
}