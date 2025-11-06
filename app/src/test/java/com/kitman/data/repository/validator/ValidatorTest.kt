package com.kitman.data.repository.validator

import org.junit.Test

class ValidatorTest {
    private val validator = Validator()

    @Test
    fun isUserNameValid() {
        assert(validator.isUserNameValid("user123"))
        assert(!validator.isUserNameValid("us"))
        assert(!validator.isUserNameValid(""))
    }

    @Test
    fun isPasswordValid() {
        assert(validator.isPasswordValid("password123"))
        assert(!validator.isPasswordValid("pa"))
        assert(!validator.isPasswordValid(""))
    }
}