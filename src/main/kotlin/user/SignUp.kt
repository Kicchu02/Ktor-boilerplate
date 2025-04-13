package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.UUIDSerializer
import com.example.user.SignUp.Request
import com.example.user.SignUp.Response
import kotlinx.serialization.Serializable
import java.util.UUID

abstract class SignUp : APIInterface<Request, Response> {
    @Serializable
    data class Request(
        val emailId: String,
        val password: String,
    ) : APIRequest

    @Serializable
    data class Response(
        @Serializable(with = UUIDSerializer::class)
        val userId: UUID,
    ) : APIResponse

    sealed class SignUpException(message: String? = null) : Exception(message) {
        class EmailAlreadyExistsException(message: String? = null) : SignUpException(message = message)
    }
}
