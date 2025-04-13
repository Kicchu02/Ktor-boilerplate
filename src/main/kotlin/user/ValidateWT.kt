package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.UUIDSerializer
import com.example.user.ValidateWT.Request
import com.example.user.ValidateWT.Response
import kotlinx.serialization.Serializable
import java.util.UUID

abstract class ValidateWT : APIInterface<Request, Response> {
    @Serializable
    data class Request(
        @Serializable(with = UUIDSerializer::class)
        val wt: UUID,
    ) : APIRequest

    @Serializable
    data class Response(
        @Serializable(with = UUIDSerializer::class)
        val userId: UUID,
        val privileges: List<String>,
    ) : APIResponse

    sealed class ValidateWTException : RuntimeException() {
        class InvalidWTException : ValidateWTException()
    }
}
