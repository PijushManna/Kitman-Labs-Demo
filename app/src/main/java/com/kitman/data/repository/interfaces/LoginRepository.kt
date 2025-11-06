package com.kitman.data.repository.interfaces

import com.kitman.data.base.BaseResponse
import com.kitman.data.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<BaseResponse<LoginResponse>>
}