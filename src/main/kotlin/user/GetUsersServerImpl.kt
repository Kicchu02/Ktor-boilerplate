package com.example.user

import com.example.DatabaseFactory
import com.example.dto.User
import com.example.user.queries.GetAllUsers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GetUsersServerImpl : GetUsers(), KoinComponent {
    private val getAllUsers by inject<GetAllUsers>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction(isReadOnly = true) { ctx ->
            val users = getAllUsers.execute(ctx = ctx, input = GetAllUsers.Input).users
            Response(users = users.map { User(id = it.id, email = it.email) })
        }
    }
}
