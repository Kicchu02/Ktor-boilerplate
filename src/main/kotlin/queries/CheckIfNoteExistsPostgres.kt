package com.example.queries

import `ktor-sample`.jooq.tables.references.NOTE
import org.jooq.DSLContext

internal class CheckIfNoteExistsPostgres : CheckIfNoteExists() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        val exists = ctx.fetchExists(
            ctx.selectFrom(NOTE)
                .where(
                    NOTE.ID.eq(input.noteId)
                        .and(NOTE.CREATEDBY.eq(input.userId))
                        .and(NOTE.ISDELETED.isFalse),
                ),
        )
        return Result(exists = exists)
    }
}
