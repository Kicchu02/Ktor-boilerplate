package com.example.queries

import com.example.queries.InsertIntoWT.Input
import com.example.queries.InsertIntoWT.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.time.Instant
import java.util.UUID

internal abstract class InsertIntoWT : QueryInterface<Input, Result> {
    data class Input(
        val wt: UUID,
        val userId: UUID,
        val expiresAt: Instant,
    ) : QueryInput

    data object Result : QueryResult
}
