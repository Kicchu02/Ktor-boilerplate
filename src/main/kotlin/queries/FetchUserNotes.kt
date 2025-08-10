package com.example.queries

import com.example.queries.FetchUserNotes.Input
import com.example.queries.FetchUserNotes.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class FetchUserNotes : QueryInterface<Input, Result> {
    data class Input(
        val userId: UUID,
    ) : QueryInput

    data class Result(
        val notes: List<NoteSummary>,
    ) : QueryResult

    data class NoteSummary(
        val id: UUID,
        val title: String,
    )
}
