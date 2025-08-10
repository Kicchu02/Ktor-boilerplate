package com.example.queries

import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.time.Instant
import java.util.UUID

internal abstract class UpdateNoteRecord : QueryInterface<UpdateNoteRecord.Input, UpdateNoteRecord.Result> {
    data class Input(
        val noteId: UUID,
        val userId: UUID,
        val title: String,
        val description: String?,
        val currentTime: Instant,
    ) : QueryInput

    data object Result : QueryResult
}
