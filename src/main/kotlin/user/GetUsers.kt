package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.User
import com.example.user.GetUsers.Request
import com.example.user.GetUsers.Response

abstract class GetUsers : APIInterface<Request, Response> {
    data object Request : APIRequest

    data class Response(
        val users: List<User>
    ) : APIResponse
}
