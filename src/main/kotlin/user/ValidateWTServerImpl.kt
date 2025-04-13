package com.example.user

import com.example.DatabaseFactory
import com.example.queries.FetchPrivilegesOfUser
import com.example.queries.FetchWTExpiresAtAndUserIdOrNull
import com.example.queries.UpdateWTExpireTime
import com.example.user.ValidateWT.ValidateWTException.InvalidWTException
import com.typesafe.config.Config
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.Instant

internal class ValidateWTServerImpl : ValidateWT(), KoinComponent {
    private val config by inject<Config>()
    private val expirationDurationInSeconds = config.getLong("token.expirationDurationInSeconds")
    private val expirationRefreshThreshold = config.getLong("token.expirationRefreshThreshold")
    private val fetchPrivilegesOfUser by inject<FetchPrivilegesOfUser>()
    private val fetchWTExpiresAtAndUserIdOrNull by inject<FetchWTExpiresAtAndUserIdOrNull>()
    private val updateWTExpireTime by inject<UpdateWTExpireTime>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction { ctx ->
            val currentTime = Instant.now()
            val (userId, expiresAt) = fetchWTExpiresAtAndUserIdOrNull.execute(
                ctx = ctx,
                input = FetchWTExpiresAtAndUserIdOrNull.Input(
                    wt = request.wt,
                    currentTime = currentTime,
                ),
            ).userIdAndExpiresAt ?: throw InvalidWTException()
            if (expiresAt < currentTime.plusSeconds(expirationRefreshThreshold)) {
                updateWTExpireTime.execute(
                    ctx = ctx,
                    input = UpdateWTExpireTime.Input(
                        wt = request.wt,
                        expiresAt = currentTime.plusSeconds(expirationDurationInSeconds),
                        currentTime = currentTime,
                    ),
                )
            }
            val userPrivileges = fetchPrivilegesOfUser.execute(
                ctx = ctx,
                input = FetchPrivilegesOfUser.Input(
                    userId = userId,
                ),
            ).privileges
            Response(
                userId = userId,
                privileges = userPrivileges,
            )
        }
    }
}
