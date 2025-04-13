package com.example.user.queries

import com.example.user.queries.CheckIfUserExistsByEmail.Input
import com.example.user.queries.CheckIfUserExistsByEmail.Result
import com.example.user.queries.QueryInterface.QueryInput
import com.example.user.queries.QueryInterface.QueryResult

internal abstract class CheckIfUserExistsByEmail : QueryInterface<Input, Result> {
    data class Input(
        val emailId: String,
    ) : QueryInput

    data class Result(
        val exists: Boolean,
    ) : QueryResult
}
