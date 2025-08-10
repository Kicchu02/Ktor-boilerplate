package com.example.note

import com.example.DatabaseFactory
import com.example.queries.FetchUserNotes
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

internal class GetNotesServerImpl(private val userId: UUID) : GetNotes(), KoinComponent {
    private val fetchUserNotes by inject<FetchUserNotes>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction(isReadOnly = true) { ctx ->
            val result = fetchUserNotes.execute(ctx = ctx, input = FetchUserNotes.Input(userId = userId))
            Response(
                notes = result.notes.map { GetNotes.NoteSummary(noteId = it.id, title = it.title) },
            )
        }
    }
}
