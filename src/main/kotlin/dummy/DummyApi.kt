package com.example.dummy

import com.example.dummy.DummyApi.Request
import com.example.dummy.DummyApi.Response
import com.example.interfaces.APIInterface
import com.example.interfaces.APIInterface.APIRequest
import com.example.interfaces.APIInterface.APIResponse
import kotlinx.serialization.Serializable

abstract class DummyApi : APIInterface<Request, Response> {
    data object Request : APIRequest

    @Serializable
    data class Response(
        val message: String,
    ) : APIResponse
}
