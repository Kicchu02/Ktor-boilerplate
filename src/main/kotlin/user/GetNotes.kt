package com.example.note

import com.example.APIInterface
import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.dto.SerializerUtils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

abstract class GetNotes : APIInterface<GetNotes.Request, GetNotes.Response> {
    data object Request : APIRequest

    @Serializable
    data class NoteSummary(
        @Serializable(with = UUIDSerializer::class)
        val noteId: UUID,
        val title: String,
    )

    @Serializable
    data class Response(
        val notes: List<NoteSummary>,
    ) : APIResponse
}
