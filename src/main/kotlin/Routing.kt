package com.example

import com.example.user.SignUp
import com.example.user.SignUp.SignUpException
import com.example.user.SignUp.SignUpException.EmailAlreadyExistsException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.getKoin

fun Application.configureRouting() {
    val signUp = getKoin().get<SignUp>()

    routing {
        post("/signUp") {
            try {
                val request = call.receive<SignUp.Request>()
                val response = signUp.execute(request = request)
                call.respond(response)
            } catch (signUpException: SignUpException) {
                when (signUpException) {
                    is EmailAlreadyExistsException -> {
                        call.response.status(HttpStatusCode.Conflict)
                        call.respondText("Email already exists.")
                    }
                }
            }
        }
    }
}
