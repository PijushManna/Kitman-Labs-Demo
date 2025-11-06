package com.kitman.data.repository.impl

import com.kitman.data.base.BaseResponse
import com.kitman.data.model.LoginResponse
import com.kitman.data.network.ApiService
import com.kitman.data.repository.interfaces.LoginRepository
import com.kitman.utils.Constants.DEFAULT_ERROR
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class LoginRepoImpl(private val apiService: ApiService) : LoginRepository {
    override fun login(
        username: String,
        password: String
    ): Flow<BaseResponse<LoginResponse>>  = channelFlow {
        trySend(BaseResponse.Loading)
        delay(2000L)
        try {
            val response = apiService.getLoginResponse()
            trySend(BaseResponse.Success(response))
        }catch (e: Exception){
            trySend(BaseResponse.Error(e.message ?: DEFAULT_ERROR))
        }
    }
}