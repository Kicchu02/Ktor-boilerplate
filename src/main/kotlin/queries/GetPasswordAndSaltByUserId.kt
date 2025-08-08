package com.example.queries

import com.example.queries.GetPasswordAndSaltByUserId.Input
import com.example.queries.GetPasswordAndSaltByUserId.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class GetPasswordAndSaltByUserId : QueryInterface<Input, Result> {
    data class Input(
        val userId: UUID,
    ) : QueryInput

    data class Result(
        val password: String,
        val salt: String,
    ) : QueryResult
}
