package com.example.queries

import com.example.getNonNullValue
import `ktor-sample`.jooq.tables.references.NOTE
import org.jooq.DSLContext

internal class FetchNoteDetailsPostgres : FetchNoteDetails() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        val record = ctx.select(NOTE.ID, NOTE.TITLE, NOTE.DESCRIPTION, NOTE.CREATEDAT)
            .from(NOTE)
            .where(
                NOTE.ID.eq(input.noteId)
                    .and(NOTE.CREATEDBY.eq(input.userId))
                    .and(NOTE.ISDELETED.isFalse),
            )
            .fetchOne()

        val note = record?.let {
            Note(
                id = it.getNonNullValue(NOTE.ID),
                title = it.getNonNullValue(NOTE.TITLE),
                description = it.get(NOTE.DESCRIPTION),
                createdAt = it.getNonNullValue(NOTE.CREATEDAT),
            )
        }
        return Result(note = note)
    }
}
