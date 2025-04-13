package com.example

import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse

interface APIInterface<Req : APIRequest, Res : APIResponse> {
    interface APIRequest

    interface APIResponse

    fun execute(request: Req): Res
}
