package com.example.note

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

abstract class CreateNote : APIInterface<CreateNote.Request, CreateNote.Response> {
    @Serializable
    data class Request(
        val title: String,
        val description: String?,
    ) : APIRequest {
        init {
            require(title.isNotBlank())
            require(title.length <= 500)
            if (description != null) require(description.length <= 2000)
        }
    }

    @Serializable
    data class Response(
        @Serializable(with = UUIDSerializer::class)
        val noteId: UUID,
    ) : APIResponse

    sealed class CreateNoteException(message: String? = null) : Exception(message) {
        class DuplicateTitleException(message: String? = null) : CreateNoteException(message)
    }
}
