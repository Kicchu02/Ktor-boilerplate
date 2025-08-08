package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.EmailId
import com.example.user.SignIn.Request
import com.example.user.SignIn.Response
import kotlinx.serialization.Serializable

abstract class SignIn : APIInterface<Request, Response> {
    @Serializable
    data class Request(
        val emailId: EmailId,
        val password: String,
    ) : APIRequest {
        init {
            require(password.isNotBlank())
        }
    }

    @Serializable
    data object Response : APIResponse

    sealed class SignInException : Exception() {
        class InvalidEmailId : SignInException()
        class InvalidPassword : SignInException()
    }
}
