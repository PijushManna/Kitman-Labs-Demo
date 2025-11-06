package com.kitman.ui.login

data class LoginState(
    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,
    val isError : Boolean = false,
    val message : String = ""
)