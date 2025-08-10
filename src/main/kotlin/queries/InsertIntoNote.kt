package com.example.queries

import com.example.queries.InsertIntoNote.Input
import com.example.queries.InsertIntoNote.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class InsertIntoNote : QueryInterface<Input, Result> {
    data class Input(
        val id: UUID,
        val title: String,
        val description: String?,
        val createdBy: UUID,
    ) : QueryInput

    data object Result : QueryResult
}
