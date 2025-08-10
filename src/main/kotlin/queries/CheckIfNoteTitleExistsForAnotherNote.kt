package com.example.queries

import com.example.queries.QueryInterface.QueryInput
import com.example.queries.QueryInterface.QueryResult
import java.util.UUID

internal abstract class CheckIfNoteTitleExistsForAnotherNote : QueryInterface<CheckIfNoteTitleExistsForAnotherNote.Input, CheckIfNoteTitleExistsForAnotherNote.Result> {
    data class Input(
        val userId: UUID,
        val excludeNoteId: UUID,
        val title: String,
    ) : QueryInput

    data class Result(
        val exists: Boolean,
    ) : QueryResult
}
