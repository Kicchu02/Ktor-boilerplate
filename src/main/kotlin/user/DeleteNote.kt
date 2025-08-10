package com.example.user

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

abstract class DeleteNote : APIInterface<DeleteNote.Request, DeleteNote.Response> {
    @Serializable
    data class Request(
        @Serializable(with = UUIDSerializer::class)
        val noteId: UUID,
    ) : APIRequest

    @Serializable
    data object Response : APIResponse

    sealed class DeleteNoteException : Exception() {
        class NoteNotFound : DeleteNoteException()
    }
}
