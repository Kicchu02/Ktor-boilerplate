package com.example.queries

import com.example.queries.DeleteNoteRecord.Input
import com.example.queries.DeleteNoteRecord.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.time.Instant
import java.util.UUID

internal abstract class DeleteNoteRecord : QueryInterface<Input, Result> {
    data class Input(
        val noteId: UUID,
        val userId: UUID,
        val currentTime: Instant,
    ) : QueryInput

    data object Result : QueryResult
}
