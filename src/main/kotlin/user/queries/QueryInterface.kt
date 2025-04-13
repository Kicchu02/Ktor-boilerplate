package com.example.user.queries

import com.example.user.queries.QueryInterface.QueryInput
import com.example.user.queries.QueryInterface.QueryResult
import org.jooq.DSLContext

interface QueryInterface<I : QueryInput, R : QueryResult> {
    interface QueryInput

    interface QueryResult

    fun execute(ctx: DSLContext, input: I): R
}
