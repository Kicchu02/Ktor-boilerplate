package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.user.DummyApi.Request
import com.example.user.DummyApi.Response
import kotlinx.serialization.Serializable

abstract class DummyApi : APIInterface<Request, Response> {
    data object Request : APIRequest

    @Serializable
    data class Response(
        val message: String,
    ) : APIResponse
}
