package com.example

import com.example.user.GetUsers
import com.example.user.SignUp
import com.example.user.SignUp.SignUpException
import com.example.user.SignUp.SignUpException.EmailAlreadyExistsException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.getKoin

fun Application.configureRouting() {
    val getUsers = getKoin().get<GetUsers>()
    val signUp = getKoin().get<SignUp>()

    routing {
        get("/") {
            call.respondText("Welcome to dummy project")
        }
        get("/getUsers") {
            val users = getUsers.execute(request = GetUsers.Request)
            val responseText = Json.encodeToString(users)
            call.respondText(responseText)
        }
        post("/signUp") {
            try {
                val request = call.receive<SignUp.Request>()
                val response = signUp.execute(request = request)
                call.response.status(HttpStatusCode.OK)
                call.respondText(Json.encodeToString(response))
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
