package com.example.queries

import com.example.dto.EmailId
import com.example.queries.CheckIfUserExistsByEmail.Input
import com.example.queries.CheckIfUserExistsByEmail.Result
import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult

internal abstract class CheckIfUserExistsByEmail : QueryInterface<Input, Result> {
    data class Input(
        val emailId: EmailId,
    ) : QueryInput

    data class Result(
        val exists: Boolean,
    ) : QueryResult
}
