package com.example

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.util.UUID
import `ktor-sample`.jooq.tables.references.USER

fun Application.configureRouting() {
    routing {
        get("/") {
            data class UserResponse(
                val id: UUID,
                val email: String,
                val isActive: Boolean
            )

//            val userIdToBeDeleted = UUID.randomUUID()
//            // Delete a user
//            DatabaseFactory.transaction { ctx ->
//                val numberOfRowsAffected = ctx.deleteFrom(USER)
//                    .where(
//                        USER.ID.eq(userIdToBeDeleted)
//                            .and(USER.ISACTIVE.eq(true))
//                    )
//                    .execute()
//                if (numberOfRowsAffected != 1) {
//                    throw Exception("Expected 1 row to be deleted, but $numberOfRowsAffected were deleted instead.")
//                }
//            }

            val users = DatabaseFactory.transaction(isReadOnly = true) { ctx ->
                ctx.select(USER.ID, USER.EMAIL, USER.ISACTIVE)
                    .from(USER)
                    .fetch()
                    .map {
                        UserResponse(
                            id = it.getNonNullValue(USER.ID),
                            email = it.getNonNullValue(USER.EMAIL),
                            isActive = it.getNonNullValue(USER.ISACTIVE)
                        )
                    }
            }

            call.request.headers.entries().forEach {
                println("header item: ${it.key}, ${it.value}")
            }
            call.respondText(users.toString())
        }
    }
}
