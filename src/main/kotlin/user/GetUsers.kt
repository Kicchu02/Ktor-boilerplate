package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.User
import com.example.user.GetUsers.Request
import com.example.user.GetUsers.Response
import kotlinx.serialization.Serializable

abstract class GetUsers : APIInterface<Request, Response> {
    @Serializable
    data object Request : APIRequest

    @Serializable
    data class Response(
        val users: List<User>,
    ) : APIResponse
}
