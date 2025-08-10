package com.example.user

import org.koin.core.component.KoinComponent
import java.util.UUID

internal class DummyApiServerImpl(private val userId: UUID) : DummyApi(), KoinComponent {
    override suspend fun execute(request: Request): Response {
        // userId is available for any per-user logic
        return Response(message = "Dummy API. UserId: $userId")
    }
}
