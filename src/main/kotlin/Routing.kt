package com.example

import com.example.note.CreateNote
import com.example.note.CreateNote.CreateNoteException
import com.example.user.DeleteNote
import com.example.user.DeleteNote.DeleteNoteException
import com.example.user.DummyApi
import com.example.user.SignIn
import com.example.user.SignIn.SignInException
import com.example.user.SignUp
import com.example.user.SignUp.SignUpException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        post("/signUp") {
            try {
                val signUp = call.inject<SignUp>()
                val request = call.receive<SignUp.Request>()
                val response = signUp.execute(request = request)
                call.respond(response)
            } catch (signUpException: SignUpException) {
                when (signUpException) {
                    is SignUpException.EmailAlreadyExistsException -> {
                        call.response.status(value = HttpStatusCode.Conflict)
                        call.respondText(text = "Email already exists.")
                    }
                }
            }
        }
        post("/signIn") {
            try {
                val signIn = call.inject<SignIn>(call)
                val request = call.receive<SignIn.Request>()
                val response = signIn.execute(request = request)
                call.respond(message = response)
            } catch (signInException: SignInException) {
                when (signInException) {
                    is SignInException.InvalidEmailId -> {
                        call.response.status(value = HttpStatusCode.Unauthorized)
                        call.respondText(text = "Email doesn't exist.")
                    }

                    is SignInException.InvalidPassword -> {
                        call.response.status(value = HttpStatusCode.Unauthorized)
                        call.respondText(text = "Invalid password.")
                    }
                }
            }
        }
        // Authenticated dummy API example
        get("/dummy") {
            val validated = call.validateAndGetUser()
            val dummy = call.inject<DummyApi>(validated.userId)
            val response = dummy.execute(request = DummyApi.Request)
            call.respond(message = response)
        }

        // Create Note endpoint
        post("/note") {
            try {
                val response = call.executeAuthenticated<CreateNote, CreateNote.Request, CreateNote.Response>(
                    request = call.receive<CreateNote.Request>(),
                )
                call.respond(message = response)
            } catch (exception: CreateNoteException) {
                when (exception) {
                    is CreateNoteException.DuplicateTitleException -> {
                        call.response.status(value = HttpStatusCode.Conflict)
                        call.respondText(text = "Note title already exists.")
                    }
                }
            }
        }

        // Delete Note endpoint
        delete("/note") {
            try {
                val response = call.executeAuthenticated<DeleteNote, DeleteNote.Request, DeleteNote.Response>(
                    request = call.receive<DeleteNote.Request>(),
                )
                call.respond(message = response)
            } catch (exception: DeleteNoteException) {
                when (exception) {
                    is DeleteNoteException.NoteNotFound -> {
                        call.response.status(value = HttpStatusCode.NotFound)
                        call.respondText(text = "Note not found.")
                    }
                }
            }
        }
    }
}
