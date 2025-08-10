package com.example

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
    }
}
