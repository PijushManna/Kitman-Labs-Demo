package com.kitman.data.repository.impl

import com.kitman.data.base.BaseResponse
import com.kitman.data.model.LoginResponse
import com.kitman.data.network.ApiService
import com.kitman.data.repository.validator.Validator
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class LoginRepoImplTest {
    private lateinit var loginRepo: LoginRepoImpl
    private val apiService: ApiService = mock()
    private val validator = Validator()

    @Before
    fun setUp(){
        loginRepo = LoginRepoImpl(apiService, validator)
    }

    @Test
    fun login() = runTest {
        `when`(apiService.getLoginResponse())
            .thenReturn(LoginResponse(status = "You have logged in successfully."))

        val res1 = loginRepo.login("user123","1234").toList()
        assert(res1[0] is BaseResponse.Success)

        val res2 = loginRepo.login("user123","").toList()
        assert(res2[0] is BaseResponse.Error)
    }
}