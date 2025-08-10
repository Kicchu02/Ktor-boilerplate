package com.example.queries

import `ktor-sample`.jooq.tables.references.NOTE
import org.jooq.DSLContext

internal class CheckIfNoteTitleExistsForAnotherNotePostgres : CheckIfNoteTitleExistsForAnotherNote() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        val exists = ctx.fetchExists(
            ctx.selectFrom(NOTE)
                .where(
                    NOTE.TITLE.eq(input.title)
                        .and(NOTE.CREATEDBY.eq(input.userId))
                        .and(NOTE.ID.ne(input.excludeNoteId))
                        .and(NOTE.ISDELETED.isFalse),
                ),
        )
        return Result(exists = exists)
    }
}
