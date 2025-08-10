package com.example.note

import com.example.DatabaseFactory
import com.example.note.CreateNote.CreateNoteException.DuplicateTitleException
import com.example.queries.CheckIfNoteTitleExists
import com.example.queries.InsertIntoNote
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

internal class CreateNoteServerImpl(private val userId: UUID) : CreateNote(), KoinComponent {
    private val checkIfNoteTitleExists by inject<CheckIfNoteTitleExists>()
    private val insertIntoNote by inject<InsertIntoNote>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction { ctx ->
            val exists = checkIfNoteTitleExists.execute(
                ctx = ctx,
                input = CheckIfNoteTitleExists.Input(userId = userId, title = request.title),
            ).exists
            if (exists) throw DuplicateTitleException("Note title already exists for the user.")

            val noteId = UUID.randomUUID()
            insertIntoNote.execute(
                ctx = ctx,
                input = InsertIntoNote.Input(
                    id = noteId,
                    title = request.title,
                    description = request.description,
                    createdBy = userId,
                ),
            )
            Response(noteId = noteId)
        }
    }
}
