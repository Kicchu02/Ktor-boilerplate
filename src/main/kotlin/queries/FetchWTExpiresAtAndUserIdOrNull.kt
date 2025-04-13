package com.example.queries

import com.example.queries.FetchWTExpiresAtAndUserIdOrNull.Input
import com.example.queries.FetchWTExpiresAtAndUserIdOrNull.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.time.Instant
import java.util.UUID

internal abstract class FetchWTExpiresAtAndUserIdOrNull : QueryInterface<Input, Result> {
    data class Input(
        val wt: UUID,
        val currentTime: Instant,
    ) : QueryInput

    data class Result(
        val userIdAndExpiresAt: UserIdAndExpiresAt?,
    ) : QueryResult

    internal data class UserIdAndExpiresAt(
        val userId: UUID,
        val expiresAt: Instant,
    )
}
