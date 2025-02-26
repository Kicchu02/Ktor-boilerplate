package com.example

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.util.reflect.TypeInfo
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import `ktor-sample`.jooq.tables.references.USER

fun Application.configureRouting() {
    routing {
        get("/") {
            data class UserResponse(
                val id: UUID,
                val email: String,
                val isActive: Boolean
            )

            val users = withContext(Dispatchers.IO) {
                DatabaseFactory.createDSLContext()
                    .select(USER.ID, USER.EMAIL, USER.ISACTIVE)
                    .from(USER)
                    .fetch()
                    .map {
                        UserResponse(
                            id = it.get(USER.ID)!!,
                            email = it.get(USER.EMAIL)!!,
                            isActive = it.get(USER.ISACTIVE)!!
                        )
                    }
            }

            call.respondText(users.toString())
        }
    }
}
