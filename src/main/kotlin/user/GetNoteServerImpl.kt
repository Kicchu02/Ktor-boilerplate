package com.example.note

import com.example.DatabaseFactory
import com.example.note.GetNote.GetNoteException.NoteNotFound
import com.example.queries.FetchNoteDetails
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

internal class GetNoteServerImpl(private val userId: UUID) : GetNote(), KoinComponent {
    private val fetchNoteDetails by inject<FetchNoteDetails>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction(isReadOnly = true) { ctx ->
            val result = fetchNoteDetails.execute(
                ctx = ctx,
                input = FetchNoteDetails.Input(noteId = request.noteId, userId = userId),
            )
            val note = result.note ?: throw NoteNotFound()
            Response(
                noteId = note.id,
                title = note.title,
                description = note.description,
                createdAt = note.createdAt,
            )
        }
    }
}
