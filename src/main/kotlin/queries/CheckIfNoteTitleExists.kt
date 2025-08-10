package com.example.queries

import com.example.queries.CheckIfNoteTitleExists.Input
import com.example.queries.CheckIfNoteTitleExists.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class CheckIfNoteTitleExists : QueryInterface<Input, Result> {
    data class Input(
        val userId: UUID,
        val title: String,
    ) : QueryInput

    data class Result(
        val exists: Boolean,
    ) : QueryResult
}
