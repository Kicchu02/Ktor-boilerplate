package com.example.queries

import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import org.jooq.DSLContext

interface QueryInterface<I : QueryInput, R : QueryResult> {
    interface QueryInput

    interface QueryResult

    fun execute(ctx: DSLContext, input: I): R
}
