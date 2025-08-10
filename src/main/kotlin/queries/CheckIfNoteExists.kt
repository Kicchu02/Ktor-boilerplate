package com.example.queries

import com.example.queries.CheckIfNoteExists.Input
import com.example.queries.CheckIfNoteExists.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class CheckIfNoteExists : QueryInterface<Input, Result> {
    data class Input(
        val noteId: UUID,
        val userId: UUID,
    ) : QueryInput

    data class Result(
        val exists: Boolean,
    ) : QueryResult
}
