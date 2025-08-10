package com.example.queries

import com.example.DBException
import `ktor-sample`.jooq.tables.references.NOTE
import org.jooq.DSLContext

internal class UpdateNoteRecordPostgres : UpdateNoteRecord() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        val status = ctx.update(NOTE)
            .set(NOTE.TITLE, input.title)
            .set(NOTE.DESCRIPTION, input.description)
            .set(NOTE.MODIFIEDAT, input.currentTime)
            .where(
                NOTE.ID.eq(input.noteId)
                    .and(NOTE.CREATEDBY.eq(input.userId))
                    .and(NOTE.ISDELETED.isFalse),
            )
            .execute()
        if (status != 1) {
            throw DBException("Failed to update note with noteId: ${input.noteId}")
        }
        return Result
    }
}
