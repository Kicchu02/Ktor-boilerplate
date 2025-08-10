package com.example.user

import com.example.DatabaseFactory
import com.example.queries.CheckIfNoteExists
import com.example.queries.DeleteNoteRecord
import com.example.user.DeleteNote.DeleteNoteException.NoteNotFound
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.Instant
import java.util.UUID

internal class DeleteNoteServerImpl(private val userId: UUID) : DeleteNote(), KoinComponent {
    private val checkIfNoteExists by inject<CheckIfNoteExists>()
    private val deleteNoteRecord by inject<DeleteNoteRecord>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction { ctx ->
            val exists = checkIfNoteExists.execute(
                ctx = ctx,
                input = CheckIfNoteExists.Input(noteId = request.noteId, userId = userId),
            ).exists
            if (!exists) throw NoteNotFound()

            deleteNoteRecord.execute(
                ctx = ctx,
                input = DeleteNoteRecord.Input(
                    noteId = request.noteId,
                    userId = userId,
                    currentTime = Instant.now(),
                ),
            )
            Response
        }
    }
}
