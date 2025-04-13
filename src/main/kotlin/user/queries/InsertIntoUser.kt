package com.example.user.queries

import com.example.dto.EmailId
import com.example.user.queries.InsertIntoUser.Input
import com.example.user.queries.InsertIntoUser.Result
import com.example.user.queries.QueryInterface.QueryInput
import com.example.user.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class InsertIntoUser : QueryInterface<Input, Result> {
    data class Input(
        val id: UUID,
        val email: EmailId,
        val password: String,
        val salt: String,
    ) : QueryInput

    data object Result : QueryResult
}
