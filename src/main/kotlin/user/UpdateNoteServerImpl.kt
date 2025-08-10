package com.example.note

import com.example.DatabaseFactory
import com.example.note.UpdateNote.UpdateNoteException.DuplicateTitleException
import com.example.note.UpdateNote.UpdateNoteException.NoteNotFound
import com.example.queries.CheckIfNoteExists
import com.example.queries.CheckIfNoteTitleExistsForAnotherNote
import com.example.queries.UpdateNoteRecord
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.Instant
import java.util.UUID

internal class UpdateNoteServerImpl(private val userId: UUID) : UpdateNote(), KoinComponent {
    private val checkIfNoteExists by inject<CheckIfNoteExists>()
    private val checkIfNoteTitleExistsForAnotherNote by inject<CheckIfNoteTitleExistsForAnotherNote>()
    private val updateNoteRecord by inject<UpdateNoteRecord>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction { ctx ->
            val exists = checkIfNoteExists.execute(
                ctx = ctx,
                input = CheckIfNoteExists.Input(noteId = request.noteId, userId = userId),
            ).exists
            if (!exists) throw NoteNotFound("Note not found or deleted.")

            val titleExists = checkIfNoteTitleExistsForAnotherNote.execute(
                ctx = ctx,
                input = CheckIfNoteTitleExistsForAnotherNote.Input(
                    userId = userId,
                    excludeNoteId = request.noteId,
                    title = request.title,
                ),
            ).exists
            if (titleExists) throw DuplicateTitleException("Note title already exists for the user.")

            updateNoteRecord.execute(
                ctx = ctx,
                input = UpdateNoteRecord.Input(
                    noteId = request.noteId,
                    userId = userId,
                    title = request.title,
                    description = request.description,
                    currentTime = Instant.now(),
                ),
            )
            Response
        }
    }
}
