package com.example

import com.example.user.GetUsers
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.getKoin

fun Application.configureRouting() {
    val getUsers = getKoin().get<GetUsers>()

    routing {
        get("/") {
            call.respondText("Welcome to dummy project")
        }
        get("/getUsers") {
            val users = getUsers.execute(request = GetUsers.Request)
            val responseText = Json.encodeToString(users)
            call.respondText(responseText)
        }
    }
}
