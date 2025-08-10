package com.example.note

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.InstantSerializer
import com.example.dto.SerializerUtils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

abstract class GetNote : APIInterface<GetNote.Request, GetNote.Response> {
    @Serializable
    data class Request(
        @Serializable(with = UUIDSerializer::class)
        val noteId: UUID,
    ) : APIRequest

    @Serializable
    data class Response(
        @Serializable(with = UUIDSerializer::class)
        val noteId: UUID,
        val title: String,
        val description: String?,
        @Serializable(with = InstantSerializer::class)
        val createdAt: Instant,
    ) : APIResponse

    sealed class GetNoteException : Exception() {
        class NoteNotFound : GetNoteException()
    }
}
