package com.example.queries

import com.example.dto.EmailId
import com.example.queries.InsertIntoUser.Input
import com.example.queries.InsertIntoUser.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
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
