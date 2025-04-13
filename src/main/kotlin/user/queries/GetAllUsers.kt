package com.example.user.queries

import com.example.user.queries.GetAllUsers.Input
import com.example.user.queries.GetAllUsers.Result
import com.example.user.queries.QueryInterface.QueryInput
import com.example.user.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class GetAllUsers : QueryInterface<Input, Result> {
    data class UserResponse(
        val id: UUID,
        val email: String,
        val isActive: Boolean,
    )

    data object Input : QueryInput

    data class Result(
        val users: List<UserResponse>,
    ) : QueryResult
}
