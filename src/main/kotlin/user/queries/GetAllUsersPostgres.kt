package com.example.user.queries

import com.example.getNonNullValue
import `ktor-sample`.jooq.tables.references.USER
import org.jooq.DSLContext

internal class GetAllUsersPostgres : GetAllUsers() {
    override fun execute(ctx: DSLContext, input: Input): Result {
        return Result(
            users = ctx.select(USER.ID, USER.EMAIL, USER.ISACTIVE)
                .from(USER)
                .fetch()
                .map {
                    UserResponse(
                        id = it.getNonNullValue(USER.ID),
                        email = it.getNonNullValue(USER.EMAIL),
                        isActive = it.getNonNullValue(USER.ISACTIVE),
                    )
                },
        )
    }
}
