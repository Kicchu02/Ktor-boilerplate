package com.example.queries

import com.example.queries.FetchNoteDetails.Input
import com.example.queries.FetchNoteDetails.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.time.Instant
import java.util.UUID

internal abstract class FetchNoteDetails : QueryInterface<Input, Result> {
    data class Input(
        val noteId: UUID,
        val userId: UUID,
    ) : QueryInput

    data class Result(
        val note: Note?,
    ) : QueryResult

    data class Note(
        val id: UUID,
        val title: String,
        val description: String?,
        val createdAt: Instant,
    )
}
