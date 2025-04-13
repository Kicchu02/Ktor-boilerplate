package com.example

import com.example.QueryInterface.QueryInput
import com.example.QueryInterface.QueryResult
import org.jooq.DSLContext

interface QueryInterface<I : QueryInput, R : QueryResult> {
    interface QueryInput

    interface QueryResult

    fun execute(ctx: DSLContext, input: I): R
}
