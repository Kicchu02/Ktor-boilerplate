package com.example.queries

import com.example.queries.FetchPrivilegesOfUser.Input
import com.example.queries.FetchPrivilegesOfUser.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class FetchPrivilegesOfUser : QueryInterface<Input, Result> {
    data class Input(
        val userId: UUID,
    ) : QueryInput

    data class Result(
        val privileges: List<String>,
    ) : QueryResult
}
