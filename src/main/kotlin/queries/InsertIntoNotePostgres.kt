package com.example.queries

import com.example.DBException
import `ktor-sample`.jooq.tables.references.NOTE
import org.jooq.DSLContext

internal class InsertIntoNotePostgres : InsertIntoNote() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        val status = ctx.insertInto(NOTE)
            .set(NOTE.ID, input.id)
            .set(NOTE.TITLE, input.title)
            .set(NOTE.DESCRIPTION, input.description)
            .set(NOTE.CREATEDBY, input.createdBy)
            .execute()
        if (status != 1) {
            throw DBException("Failed to insert note with id: ${input.id} into Note table.")
        }
        return Result
    }
}
