package com.example.note

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

abstract class UpdateNote : APIInterface<UpdateNote.Request, UpdateNote.Response> {
    @Serializable
    data class Request(
        @Serializable(with = UUIDSerializer::class)
        val noteId: UUID,
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
    data object Response : APIResponse

    sealed class UpdateNoteException(message: String? = null) : Exception(message) {
        class DuplicateTitleException(message: String? = null) : UpdateNoteException(message)
        class NoteNotFound(message: String? = null) : UpdateNoteException(message)
    }
}
