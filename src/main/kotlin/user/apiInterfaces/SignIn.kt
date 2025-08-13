package com.example.user.apiInterfaces

import com.example.dto.EmailId
import com.example.interfaces.APIInterface
import com.example.interfaces.APIInterface.APIRequest
import com.example.interfaces.APIInterface.APIResponse
import com.example.user.apiInterfaces.SignIn.Request
import com.example.user.apiInterfaces.SignIn.Response
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

    sealed class SignInException(message: String? = null) : Exception(message) {
        class InvalidEmailId(message: String? = null) : SignInException(message = message)
        class InvalidPassword(message: String? = null) : SignInException(message = message)
    }
}
