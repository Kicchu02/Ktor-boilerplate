package com.example.queries

import com.example.getNonNullValue
import `ktor-sample`.jooq.tables.references.NOTE
import org.jooq.DSLContext

internal class FetchUserNotesPostgres : FetchUserNotes() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        val notes = ctx.select(NOTE.ID, NOTE.TITLE)
            .from(NOTE)
            .where(
                NOTE.CREATEDBY.eq(input.userId)
                    .and(NOTE.ISDELETED.isFalse),
            )
            .orderBy(NOTE.CREATEDAT.desc())
            .fetch()
            .map {
                NoteSummary(
                    id = it.getNonNullValue(NOTE.ID),
                    title = it.getNonNullValue(NOTE.TITLE),
                )
            }
        return Result(notes = notes)
    }
}
