package com.example.queries

import com.example.dto.EmailId
import com.example.queries.GetUserIdByEmail.Input
import com.example.queries.GetUserIdByEmail.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class GetUserIdByEmail : QueryInterface<Input, Result> {
    data class Input(
        val emailId: EmailId,
    ) : QueryInput

    data class Result(
        val userId: UUID,
    ) : QueryResult
}
