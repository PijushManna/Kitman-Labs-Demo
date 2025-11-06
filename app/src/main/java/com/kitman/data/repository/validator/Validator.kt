package com.kitman.data.repository.validator

class Validator {
    fun isUserNameValid(userName: String): Boolean{
        return userName.trim().length >= 3
    }

    fun isPasswordValid(password: String): Boolean{
        return password.length >= 3
    }
}