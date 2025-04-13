package com.example.queries

import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import com.example.queries.UpdateWTExpireTime.Input
import com.example.queries.UpdateWTExpireTime.Result
import java.time.Instant
import java.util.UUID

internal abstract class UpdateWTExpireTime : QueryInterface<Input, Result> {
    data class Input(
        val wt: UUID,
        val expiresAt: Instant,
        val currentTime: Instant,
    ) : QueryInput

    data object Result : QueryResult
}
