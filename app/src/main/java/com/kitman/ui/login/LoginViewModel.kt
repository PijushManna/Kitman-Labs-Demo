package com.kitman.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitman.data.base.BaseResponse
import com.kitman.data.repository.interfaces.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(val repository: LoginRepository) : ViewModel() {
    val loginState = MutableStateFlow(LoginState())

    fun login(username: String, password: String){
        viewModelScope.launch {
            repository.login(username, password).collect {
                loginState.value = when(it){
                    is BaseResponse.Success -> {
                        LoginState(isSuccess = true, message = it.data.status)
                    }
                    is BaseResponse.Error -> {
                        LoginState(isError = true, message = it.message)
                    }
                    BaseResponse.Loading -> {
                        LoginState(isLoading = true)
                    }
                }
            }
        }
    }
}
